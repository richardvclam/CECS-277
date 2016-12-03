package network_fwg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Panel extends JPanel implements ActionListener {
	
	/**
	 * Fire button
	 */
	private JButton btnFire;
	/**
	 * Water button
	 */
	private JButton btnWater;
	/**
	 * Grass button
	 */
	private JButton btnGrass;
	
	private FWGClient client;
	private BufferedImage background;
	private BufferedImage win;
	private BufferedImage lose;
	private BufferedImage tie;
	private BufferedImage shadow;
	private BufferedImage[] playerImg = new BufferedImage[3];
	private BufferedImage[] cpuImg = new BufferedImage[3];

	public Panel(FWGClient client) {
		
		this.client = client;
		
		// User Interface
		setBackground(Color.WHITE);
		//setLayout(new FlowLayout(FlowLayout.CENTER));
		//setLayout(new GridBagLayout());
		setLayout(null);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.HORIZONTAL;
		gbc.fill = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.PAGE_END;
		// Fire Button
		btnFire = new JButton("Fire", new ImageIcon("src/network_fwg/img/fireButton.png"));
		btnFire.addActionListener(this);
		btnFire.setHorizontalTextPosition(AbstractButton.CENTER);
		btnFire.setVerticalTextPosition(AbstractButton.BOTTOM);
		btnFire.setBounds(100, 460, (int) btnFire.getPreferredSize().getWidth(), (int) btnFire.getPreferredSize().getHeight());
		//btnFire.setAlignmentX(Component.CENTER_ALIGNMENT);
		//add(btnFire, gbc);
		add(btnFire);
		// Water Button
		btnWater = new JButton("Water", new ImageIcon("src/network_fwg/img/waterButton.png"));
		btnWater.addActionListener(this);
		btnWater.setHorizontalTextPosition(AbstractButton.CENTER);
		btnWater.setVerticalTextPosition(AbstractButton.BOTTOM);
		btnWater.setBounds(200, 460, (int) btnFire.getPreferredSize().getWidth(), (int) btnFire.getPreferredSize().getHeight());
		//btnWater.setAlignmentX(Component.CENTER_ALIGNMENT);
		//add(btnWater, gbc);
		add(btnWater);
		// Grass Button
		btnGrass = new JButton("Grass", new ImageIcon("src/network_fwg/img/grassButton.png"));
		btnGrass.addActionListener(this);
		btnGrass.setHorizontalTextPosition(AbstractButton.CENTER);
		btnGrass.setVerticalTextPosition(AbstractButton.BOTTOM);
		btnGrass.setBounds(300, 460, (int) btnFire.getPreferredSize().getWidth(), (int) btnFire.getPreferredSize().getHeight());
		//btnGrass.setAlignmentX(Component.CENTER_ALIGNMENT);
		//add(btnGrass, gbc);
		add(btnGrass);
		
		// Load Resources
		try {
			background = ImageIO.read(new File("src/network_fwg/img/back.png"));
			shadow = ImageIO.read(new File("src/network_fwg/img/shadow.png"));
			win = ImageIO.read(new File("src/network_fwg/img/win.png"));
			lose = ImageIO.read(new File("src/network_fwg/img/lose.png"));
			tie = ImageIO.read(new File("src/network_fwg/img/tie.png"));
			for (int i = 0; i < 3; i++) {
				playerImg[i] = ImageIO.read(new File("src/network_fwg/img/player" + (i+1) + ".png"));
			}
			for (int i = 0; i < 3; i++) {
				cpuImg[i] = ImageIO.read(new File("src/network_fwg/img/opp" + (i+1) + ".png"));
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this);
		if (client.getPlayerMove() > 0 && client.getCpuMove() > 0) {
			renderResult(g, client.getPlayerMove(), client.getCpuMove());
		}
		renderScore(g, client.getPlayerWins(), client.getTies(), client.getCpuWins());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnFire) {
			client.write("1");
		} else if (e.getSource() == btnWater) {
			client.write("2");
		} else if (e.getSource() == btnGrass) {
			client.write("3");
		}
		
	}
	
	public void renderResult(Graphics g, int playerMove, int cpuMove) {
		String str = "";
		
		g.drawImage(shadow, 30, 390, this);
		g.drawImage(shadow, 270, 390, this);
		
		// Draw player & cpu choices
		g.drawImage(playerImg[playerMove - 1], 0, 170, this);
		g.drawImage(cpuImg[cpuMove - 1], 250, 170, this);
		
		Font font = new Font("Arial", Font.BOLD, 20);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Player", 30, 170);
		g.drawString("Computer", 380, 170);
		
		
		if ((playerMove == 1 && cpuMove == 1) || (playerMove == 2 && cpuMove == 2) || (playerMove == 3 && cpuMove == 3)) {
			g.drawImage(tie, 165, 30, this);
		} else if ((playerMove == 1 && cpuMove == 2) || (playerMove == 2 && cpuMove == 3) || (playerMove == 3 && cpuMove == 1)) {
			g.drawImage(lose, 130, 30, this);
		} else if ((playerMove == 1 && cpuMove == 3) || (playerMove == 2 && cpuMove == 1) || (playerMove == 3 && cpuMove == 2)) {
			g.drawImage(win, 150, 30, this);
		}
		//g.drawString(str, 150, 500);
	}
	
	public void renderScore(Graphics g, int playerWins, int ties, int cpuWins) {
		Font font = new Font("Arial", Font.BOLD, 20);
		g.setFont(font);
		g.drawString("Player: " + playerWins, 50, 20);
		g.drawString("Ties: " + ties, 200, 20);
		g.drawString("Computer: " + cpuWins, 320, 20);
	}
	
}
