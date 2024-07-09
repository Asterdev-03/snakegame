package com.aswin.game;

public class GameThread extends Thread {

    private GamePanel gamePanel;
    private volatile boolean running = true;

    public GameThread(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        while (running) {
            gamePanel.updateGame();
            gamePanel.repaint();

            try {
                Thread.sleep(75);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopGame() {
        running = false;
    }

    public void startGame() {
        running = true;
        this.start();
    }
}
