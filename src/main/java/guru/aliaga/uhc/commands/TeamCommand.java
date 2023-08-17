package guru.aliaga.uhc.commands;

import guru.aliaga.uhc.UHC;
import guru.aliaga.uhc.game.GameState;
import guru.aliaga.uhc.player.UPlayer;
import guru.aliaga.uhc.player.UPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TeamCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof UPlayer)){
            System.out.println("You must be a player to execute this command.");
            return true;
        }

        Player player = (Player) sender;
        if(args.length == 0){
            //send usage
            return true;
        }

        if(GameState.getCurrentGameState() != GameState.PRE_GAME){
            player.sendMessage("You can only use this command in the pre-game.");
            return true;
        }

        switch(args[0].toLowerCase()){
            case "create": {
                UPlayer uplayer = UHC.getInstance().getPlayerManager().getPlayer(player.getUniqueId());
                if(uplayer.getTeam() != null){
                    player.sendMessage("You are already in a team.");
                    return true;
                }

                UHC.getInstance().getTeamManager().createTeam(player.getUniqueId());
                player.sendMessage("You have created a team.");
                break;
            }
            case "invite": {
                if(args.length != 2){
                    player.sendMessage("Usage: /team invite <player>");
                    return true;
                }

                UPlayer uplayer = UHC.getInstance().getPlayerManager().getPlayer(player.getUniqueId());
                if(uplayer.getTeam() == null){
                    player.sendMessage("You are not in a team.");
                    return true;
                }

                if(!uplayer.getTeam().getLeader().equals(player.getUniqueId())){
                    player.sendMessage("You are not the leader of your team.");
                    return true;
                }

                Player target = UHC.getInstance().getServer().getPlayer(args[1]);
                if(target == null){
                    player.sendMessage("Player not found.");
                    return true;
                }

                UPlayer targetUPlayer = UHC.getInstance().getPlayerManager().getPlayer(target.getUniqueId());
                if(targetUPlayer.getTeam() != null){
                    player.sendMessage("Player is already in a team.");
                    return true;
                }

                uplayer.getTeam().invite(target.getUniqueId());
                player.sendMessage("You have invited " + target.getName() + " to your team. Type /team accept " + player.getName() + " to join.");
                target.sendMessage("You have been invited to " + player.getName() + "'s team.");
                break;
            }
            case "accept": {
                UPlayer uplayer = UHC.getInstance().getPlayerManager().getPlayer(player.getUniqueId());
                if(uplayer.getTeam() != null){
                    player.sendMessage("You are already in a team.");
                    return true;
                }

                UPlayer inviterUPlayer = UHC.getInstance().getPlayerManager().getPlayer(args[1]);
                if(inviterUPlayer == null){
                    player.sendMessage("Player not found.");
                    return true;
                }

                if(inviterUPlayer.getTeam() == null){
                    player.sendMessage("Player is not in a team.");
                    return true;
                }

                if(!inviterUPlayer.getTeam().getInvitedMembers().contains(player.getUniqueId())){
                    player.sendMessage("You have not been invited to this team.");
                    return true;
                }

                inviterUPlayer.getTeam().getInvitedMembers().remove(player.getUniqueId());
                inviterUPlayer.getTeam().getMembers().add(player.getUniqueId());
                uplayer.setTeam(inviterUPlayer.getTeam());
                player.sendMessage("You have joined " + inviterUPlayer.getName() + "'s team.");
                inviterUPlayer.sendMessage(player.getName() + " has joined your team.");
                break;
            }
            case "deny": {
                UPlayer uplayer = UHC.getInstance().getPlayerManager().getPlayer(player.getUniqueId());
                if(uplayer.getTeam() != null){
                    player.sendMessage("You are already in a team.");
                    return true;
                }

                UPlayer inviterUPlayer = UHC.getInstance().getPlayerManager().getPlayer(args[1]);
                if(inviterUPlayer == null){
                    player.sendMessage("Player not found.");
                    return true;
                }
                if(inviterUPlayer.getTeam() == null){
                    player.sendMessage("Player is not in a team.");
                    return true;
                }

                if(!inviterUPlayer.getTeam().getInvitedMembers().contains(player.getUniqueId())){
                    player.sendMessage("You have not been invited to this team.");
                    return true;
                }

                inviterUPlayer.getTeam().getInvitedMembers().remove(player.getUniqueId());
                player.sendMessage("You have denied the invitation to " + inviterUPlayer.getName() + "'s team.");
                inviterUPlayer.getTeam().sendMessage(player.getName() + " has denied the invitation to your team.");
                break;
            }
            case "kick": {
                if(args.length != 2){
                    player.sendMessage("Usage: /team kick <player>");
                    return true;
                }

                UPlayer uplayer = UHC.getInstance().getPlayerManager().getPlayer(player.getUniqueId());
                if(uplayer.getTeam() == null){
                    player.sendMessage("You are not in a team.");
                    return true;
                }

                if(!uplayer.getTeam().getLeader().equals(player.getUniqueId())){
                    player.sendMessage("You are not the leader of your team.");
                    return true;
                }

                UPlayer targetUPlayer = UHC.getInstance().getPlayerManager().getPlayer(args[1]);
                if(targetUPlayer == null){
                    player.sendMessage("Player is not on your team.");
                    return true;
                }

                if(!uplayer.getTeam().getMembers().contains(targetUPlayer.getUniqueId())){
                    player.sendMessage("Player is not in your team.");
                    return true;
                }

                uplayer.getTeam().getMembers().remove(targetUPlayer.getUniqueId());
                targetUPlayer.setTeam(null);
                player.sendMessage("You have kicked " + targetUPlayer.getName() + " from your team.");
                targetUPlayer.sendMessage("You have been kicked from " + player.getName() + "'s team.");
                break;
            }
            case "leave": {
                UPlayer uplayer = UHC.getInstance().getPlayerManager().getPlayer(player.getUniqueId());
                if(uplayer.getTeam() == null){
                    player.sendMessage("You are not in a team.");
                    return true;
                }

                if(uplayer.getTeam().getLeader().equals(player.getUniqueId())){
                    player.sendMessage("You are the leader of your team. Use /team disband to disband your team.");
                    return true;
                }

                uplayer.getTeam().getMembers().remove(player.getUniqueId());
                uplayer.setTeam(null);
                player.sendMessage("You have left your team.");
                break;
            }
            case "disband": {
                UPlayer uplayer = UHC.getInstance().getPlayerManager().getPlayer(player.getUniqueId());
                if(uplayer.getTeam() == null){
                    player.sendMessage("You are not in a team.");
                    return true;
                }

                if(!uplayer.getTeam().getLeader().equals(player.getUniqueId())){
                    player.sendMessage("You are not the leader of your team.");
                    return true;
                }

                UUID teamId = uplayer.getTeam().getUniqueId();

                for(UUID member : uplayer.getTeam().getMembers()){
                    UPlayer memberUPlayer = UHC.getInstance().getPlayerManager().getPlayer(member);
                    Player target = Bukkit.getPlayer(member);
                    if(target != null){
                        target.sendMessage("Your team has been disbanded.");
                    }

                    memberUPlayer.setTeam(null);
                }

                UHC.getInstance().getTeamManager().disbandTeam(teamId);
                break;

            }
            case "leader": {
                if(args.length != 2){
                    player.sendMessage("Usage: /team leader <player>");
                    return true;
                }

                UPlayer uplayer = UHC.getInstance().getPlayerManager().getPlayer(player.getUniqueId());
                if(uplayer.getTeam() == null){
                    player.sendMessage("You are not in a team.");
                    return true;
                }

                if(!uplayer.getTeam().getLeader().equals(player.getUniqueId())){
                    player.sendMessage("You are not the leader of your team.");
                    return true;
                }

                Player target = Bukkit.getPlayer(args[1]);
                if(target == null){
                    player.sendMessage("Player not found.");
                    return true;
                }

                UPlayer targetUPlayer = UHC.getInstance().getPlayerManager().getPlayer(target.getUniqueId());
                if(!uplayer.getTeam().getMembers().contains(target.getUniqueId())){
                    player.sendMessage("Player is not in your team.");
                    return true;
                }

                uplayer.getTeam().setLeader(target.getUniqueId());
                player.sendMessage("You have set " + target.getName() + " as the leader of your team.");
                target.sendMessage("You have been set as the leader of " + player.getName() + "'s team.");
                break;

            }
            case "info": {
                UPlayer uplayer = UHC.getInstance().getPlayerManager().getPlayer(player.getUniqueId());
                if(uplayer.getTeam() == null){
                    player.sendMessage("You are not in a team.");
                    return true;
                }

                player.sendMessage("Team info:");
                player.sendMessage("Leader: " + Bukkit.getPlayer(uplayer.getTeam().getLeader()).getName());
                player.sendMessage("Members:");
                for(UUID member : uplayer.getTeam().getMembers()){
                    player.sendMessage("- " + UHC.getInstance().getPlayerManager().getPlayer(member).getName());
                }
                break;
            }
            default:
                //send usage
        }


        return false;
    }
}
