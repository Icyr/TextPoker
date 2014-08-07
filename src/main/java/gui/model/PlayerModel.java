package gui.model;

import entities.players.Player;

public class PlayerModel
{
    private Player player;
    private int number;

    public PlayerModel(Player player, int number)
    {
        this.player = player;
        this.number = number;
    }

    public String getDescription()
    {
        return "Player #" + number + "(" + player.getId() + ")";
    }
}
