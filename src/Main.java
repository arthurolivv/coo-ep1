import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/***********************************************************************/
/*                                                                     */
/* Para jogar:                                                         */
/*                                                                     */
/*    - cima, baixo, esquerda, direita: movimentação do player.        */
/*    - control: disparo de projéteis.                                 */
/*    - ESC: para sair do jogo.                                        */
/*                                                                     */
/***********************************************************************/

public class Main {

	/* Constantes relacionadas aos estados que os elementos   */
	/* do jogo (player, projeteis ou inimigos) podem assumir. */

	public static final int INACTIVE = 0;
	public static final int ACTIVE = 1;
	public static final int EXPLODING = 2;

	public static double spawnX = GameLib.WIDTH * 0.20;
	public static int count = 0;

	/* Espera, sem fazer nada, até que o instante de tempo atual seja */
	/* maior ou igual ao instante especificado no parâmetro "time.    */

	public static void busyWait(long time) {

		while (System.currentTimeMillis() < time) Thread.yield();
	}

	/* Encontra e devolve o primeiro índice do  */
	/* array referente a uma posição "inativa". */

	public static int findFreeIndex(int[] stateArray) {

		int i;

		for (i = 0; i < stateArray.length; i++) {

			if (stateArray[i] == INACTIVE) break;
		}

		return i;
	}

	/* Encontra e devolve o conjunto de índices (a quantidade */
	/* de índices é defnida através do parâmetro "amount") do */
	/* array referente a posições "inativas".                 */

	public static int[] findFreeIndex(int[] stateArray, int amount) {

		int i, k;
		int[] freeArray = new int[amount];

		for (i = 0; i < freeArray.length; i++) freeArray[i] = stateArray.length;

		for (i = 0, k = 0; i < stateArray.length && k < amount; i++) {

			if (stateArray[i] == INACTIVE) {

				freeArray[k] = i;
				k++;
			}
		}

		return freeArray;
	}

	/* Método principal */

	public static void main(String[] args) {

		/* Indica que o jogo está em execução */

		boolean running = true;

		/* variáveis usadas no controle de tempo efetuado no main loop */

		long delta;
		long currentTime = System.currentTimeMillis();

		long nextEnemy1 = currentTime + 2000;
		long nextEnemy2 = currentTime + 7000;

		/* variáveis do player */

		Player player = new Player(
				ACTIVE,
				GameLib.WIDTH / 2,
				GameLib.HEIGHT * 0.90,
				0.25,
				0.25,
				12.0,
				0,
				0,
				currentTime
		);

		/* variáveis dos projéteis disparados pelo player */

		int[] projectile_states = new int[10];                    // estados
		double[] projectile_X = new double[10];                // coordenadas x
		double[] projectile_Y = new double[10];                // coordenadas y
		double[] projectile_VX = new double[10];                // velocidades no eixo x
		double[] projectile_VY = new double[10];                // velocidades no eixo y

		/* variáveis dos inimigos tipo 1 e 2 */

//		Enemy enemyManager = new EnemyManager();
//
//		for (int i = 0; i < 10; i++) {
//			enemyManager.addEnemy(new Enemy1(ACTIVE, 0, 0, 0, 0, 9.0, 0,
//					0, 0, 0, 0, nextEnemy1, 0));
//			enemyManager.addEnemy(new Enemy2(ACTIVE, 0, 0, 0, 0, 12.0, 0,
//					0, 0, 0, 0, nextEnemy2, count++));
//		}

		List<Enemy> allEnemies = new ArrayList<>();

// Criando 10 inimigos do tipo Enemy1
		for (int i = 0; i < 10; i++) {
			Enemy1 enemy1 = new Enemy1(
					GameStates.ACTIVE.getValue(),
					Math.random() * (GameLib.WIDTH - 20.0) + 10.0,
					-10.0,
					0.0,
					0.0,
					9.0,
					0.20 + Math.random() * 0.15,
					(3 * Math.PI) / 2,
					0.0,
					0.0,
					0.0,
					currentTime + 500,
					currentTime + 500
			);

			allEnemies.add(enemy1);
		}

// Criando 10 inimigos do tipo Enemy2
		for (int i = 0; i < 10; i++) {
			Enemy2 enemy2 = new Enemy2(
					GameStates.ACTIVE.getValue(),
					Math.random() > 0.5 ? GameLib.WIDTH * 0.2 : GameLib.WIDTH * 0.8,
					-10.0,
					0.0,
					0.0,
					12.0,
					0.42,
					(3 * Math.PI) / 2,
					0.0,
					0.0,
					0.0,
					currentTime + 3000 + (long)(Math.random() * 3000),
					i // count
			);

			allEnemies.add(enemy2);
		}


		/* variáveis dos projéteis lançados pelos inimigos (tanto tipo 1, quanto tipo 2) */

		int[] e_projectile_states = new int[200];                // estados
		double[] e_projectile_X = new double[200];                // coordenadas x
		double[] e_projectile_Y = new double[200];                // coordenadas y
		double[] e_projectile_VX = new double[200];                // velocidade no eixo x
		double[] e_projectile_VY = new double[200];                // velocidade no eixo y
		double e_projectile_radius = 2.0;                    // raio (tamanho dos projéteis inimigos)

		/* estrelas que formam o fundo de primeiro plano */

		double[] background1_X = new double[20];
		double[] background1_Y = new double[20];
		double background1_speed = 0.070;
		double background1_count = 0.0;

		/* estrelas que formam o fundo de segundo plano */

		double[] background2_X = new double[50];
		double[] background2_Y = new double[50];
		double background2_speed = 0.045;
		double background2_count = 0.0;

		/* inicializações */

		for (int i = 0; i < projectile_states.length; i++) projectile_states[i] = INACTIVE;
		for (int i = 0; i < e_projectile_states.length; i++) e_projectile_states[i] = INACTIVE;

		for (int i = 0; i < background1_X.length; i++) {

			background1_X[i] = Math.random() * GameLib.WIDTH;
			background1_Y[i] = Math.random() * GameLib.HEIGHT;
		}

		for (int i = 0; i < background2_X.length; i++) {

			background2_X[i] = Math.random() * GameLib.WIDTH;
			background2_Y[i] = Math.random() * GameLib.HEIGHT;
		}

		/* iniciado interface gráfica */

		GameLib.initGraphics();
		//GameLib.initGraphics_SAFE_MODE();  // chame esta versão do método caso nada seja desenhado na janela do jogo.

		/*************************************************************************************************/
		/*                                                                                               */
		/* Main loop do jogo                                                                             */
		/* -----------------                                                                             */
		/*                                                                                               */
		/* O main loop do jogo executa as seguintes operações:                                           */
		/*                                                                                               */
		/* 1) Verifica se há colisões e atualiza estados dos elementos conforme a necessidade.           */
		/*                                                                                               */
		/* 2) Atualiza estados dos elementos baseados no tempo que correu entre a última atualização     */
		/*    e o timestamp atual: posição e orientação, execução de disparos de projéteis, etc.         */
		/*                                                                                               */
		/* 3) Processa entrada do usuário (teclado) e atualiza estados do player conforme a necessidade. */
		/*                                                                                               */
		/* 4) Desenha a cena, a partir dos estados dos elementos.                                        */
		/*                                                                                               */
		/* 5) Espera um período de tempo (de modo que delta seja aproximadamente sempre constante).      */
		/*                                                                                               */
		/*************************************************************************************************/

		while (running) {

			/* Usada para atualizar o estado dos elementos do jogo    */
			/* (player, projéteis e inimigos) "delta" indica quantos  */
			/* ms se passaram desde a última atualização.             */

			delta = System.currentTimeMillis() - currentTime;

			/* Já a variável "currentTime" nos dá o timestamp atual.  */

			currentTime = System.currentTimeMillis();

			/***************************/
			/* Verificação de colisões */
			/***************************/

			if (player.getState() == ACTIVE) {

				/* colisões player - projeteis (inimigo) */

				for (int i = 0; i < e_projectile_states.length; i++) {

					double dx = e_projectile_X[i] - player.getX();
					double dy = e_projectile_Y[i] - player.getY();
					double dist = Math.sqrt(dx * dx + dy * dy);

					if (dist < (player.getRadius() + e_projectile_radius) * 0.8) {

						player.setState(EXPLODING);
						player.setExplosion_start(currentTime);
						player.setExplosion_end(currentTime + 2000);
					}
				}

				/* colisões player - inimigos */

				for (Enemy enemy : allEnemies) {
					if (enemy.getState() != GameStates.ACTIVE.getValue()) continue;

					double dx = enemy.getX() - player.getX();
					double dy = enemy.getY() - player.getY();
					double dist = Math.sqrt(dx * dx + dy * dy);

					if (dist < (enemy.getRadius() + player.getRadius()) * 0.8) {
						player.setState(EXPLODING);
						player.setExplosion_start(currentTime);
						player.setExplosion_end(currentTime + 2000);
//						break; // para evitar múltiplas colisões no mesmo instante
					}
				}

			}

			/* colisões projeteis (player) - inimigos */

			for (int k = 0; k < projectile_states.length; k++) {

				for (Enemy enemy : allEnemies) {

					if (enemy.getState() != ACTIVE) continue;

					double dx = enemy.getX() - projectile_X[k]; // ← projétil atual
					double dy = enemy.getY() - projectile_Y[k];
					double dist = Math.sqrt(dx * dx + dy * dy);

					if (dist < enemy.getRadius()) {
						enemy.setState(EXPLODING);
						enemy.setExplosion_start(currentTime);
						enemy.setExplosion_end(currentTime + 500);
//						break; // se 1 projétil só pode atingir 1 inimigo
					}
				}
			}

			/***************************/
			/* Atualizações de estados */
			/***************************/

			/* projeteis (player) */

			for (int i = 0; i < projectile_states.length; i++) {

				if (projectile_states[i] == ACTIVE) {

					/* verificando se projétil saiu da tela */
					if (projectile_Y[i] < 0) {

						projectile_states[i] = INACTIVE;
					} else {

						projectile_X[i] += projectile_VX[i] * delta;
						projectile_Y[i] += projectile_VY[i] * delta;
					}
				}
			}

			/* projeteis (inimigos) */

			for (int i = 0; i < e_projectile_states.length; i++) {

				if (e_projectile_states[i] == ACTIVE) {

					/* verificando se projétil saiu da tela */
					if (e_projectile_Y[i] > GameLib.HEIGHT) {

						e_projectile_states[i] = INACTIVE;
					} else {

						e_projectile_X[i] += e_projectile_VX[i] * delta;
						e_projectile_Y[i] += e_projectile_VY[i] * delta;
					}
				}
			}

			/* inimigos tipo 1 e 2*/

			for (Enemy enemy : allEnemies) {

				if (enemy instanceof Enemy1) {
					Enemy1 e1 = (Enemy1) enemy;

					if (e1.getState() == EXPLODING) {
						if (currentTime > e1.getExplosion_end()) {
							e1.setState(INACTIVE);
						}
					}

					if (e1.getState() == ACTIVE) {

						if (e1.getY() > GameLib.HEIGHT + 10) {
							e1.setState(INACTIVE);
						} else {
							e1.setX(enemy.getX() + e1.getV() * Math.cos(e1.getAngle()) * delta);
							e1.setY(enemy.getY() + e1.getV() * Math.sin(e1.getAngle()) * delta * (-1.0));
							e1.setAngle(e1.getAngle() + e1.getRv() * delta);

							if (currentTime > e1.getNextShoot() && e1.getY() < player.getY()) {

								int free = findFreeIndex(e_projectile_states);

								if (free < e_projectile_states.length) {

									e_projectile_X[free] = e1.getX();
									e_projectile_Y[free] = e1.getY();
									e_projectile_VX[free] = Math.cos(e1.getAngle()) * 0.45;
									e_projectile_VY[free] = Math.sin(e1.getAngle()) * 0.45 * (-1.0);
									e_projectile_states[free] = ACTIVE;

									e1.setNextShoot((long) (currentTime + 200 + Math.random() * 500));
								}
							}
						}
					}
				}
					else if (enemy instanceof Enemy2) {
						Enemy2 e2 = (Enemy2) enemy;

						if (e2.getState() == EXPLODING) {
							if (currentTime > e2.getExplosion_end()) {
								e2.setState(INACTIVE);
							}
						}
						if (e2.getState() == ACTIVE) {


							if (e2.getX() < -10 || e2.getX() > GameLib.WIDTH + 10) {
								e2.setState(INACTIVE);
							} else {
								boolean shootNow = false;
								double previousY = e2.getY();

								e2.setX(e2.getX() + e2.getV() * Math.cos(e2.getAngle()) * delta);
								e2.setY(e2.getY() + e2.getV() * Math.sin(e2.getAngle()) * delta * (-1.0));
								e2.setAngle(e2.getAngle() + e2.getRv() * delta);

								double threshold = GameLib.HEIGHT * 0.30;

								if (previousY < threshold && e2.getY() >= threshold) {

									if (e2.getRv() < GameLib.WIDTH / 2) e2.setRv(0.003);
									else e2.setRv(-0.003);
								}

								if (e2.getRv() > 0 && Math.abs(e2.getAngle() - 3 * Math.PI) < 0.05) {

									e2.setRv(0.0);
									e2.setAngle(3 * Math.PI);
									shootNow = true;
								}

								if (e2.getRv() < 0 && Math.abs(e2.getAngle()) < 0.05) {

									e2.setRv(0.0);
									e2.setAngle(0.0);
									shootNow = true;
								}

								if (shootNow) {

									double[] angles = {Math.PI / 2 + Math.PI / 8, Math.PI / 2, Math.PI / 2 - Math.PI / 8};
									int[] freeArray = findFreeIndex(e_projectile_states, angles.length);

									for (int k = 0; k < freeArray.length; k++) {

										int free = freeArray[k];

										if (free < e_projectile_states.length) {

											double a = angles[k] + Math.random() * Math.PI / 6 - Math.PI / 12;
											double vx = Math.cos(a);
											double vy = Math.sin(a);

											e_projectile_X[free] = e2.getX();
											e_projectile_Y[free] = e2.getY();
											e_projectile_VX[free] = vx * 0.30;
											e_projectile_VY[free] = vy * 0.30;
											e_projectile_states[free] = ACTIVE;
										}
									}
								}
							}
						}

					}
			}


			/* verificando se novos inimigos (tipo 1 e 2) devem ser "lançados" */


//					if (currentTime > nextEnemy1) {
//						enemyManager.addEnemy(new Enemy1(ACTIVE, Math.random() * (GameLib.WIDTH - 20.0) + 10.0, -10, 0, 0, 9.0, 0.20 + Math.random() * 0.15, (3 * Math.PI) / 2, 0, 0, 0, currentTime + 500, currentTime + 500));
//
//					}

			if (currentTime > nextEnemy1) {
				allEnemies.add(new Enemy1(
						ACTIVE,
						Math.random() * (GameLib.WIDTH - 20.0) + 10.0,
						-10,
						0, 0,
						9.0,
						0.20 + Math.random() * 0.15,
						(3 * Math.PI) / 2,
						0, 0, 0,
						currentTime + 500,
						currentTime + 500
				));
			}



			if (currentTime > nextEnemy2) {
				allEnemies.add(new Enemy2(
						ACTIVE,
						spawnX, -10.0,
						0, 0,
						12.0,
						0.42,
						(3 * Math.PI) / 2,
						0, 0, 0,
						currentTime + 120,
						count++
				));

				if (count < 10) {
					nextEnemy2 = currentTime + 120;
				} else {
					count = 0;
					spawnX = Math.random() > 0.5 ? GameLib.WIDTH * 0.2 : GameLib.WIDTH * 0.8;
					nextEnemy2 = (long) (currentTime + 3000 + Math.random() * 3000);
				}
			}



			/* Verificando se a explosão do player já acabou.         */
			/* Ao final da explosão, o player volta a ser controlável */
			if (player.getState() == EXPLODING) {

				if (currentTime > player.getExplosion_end()) {

					player.setState(ACTIVE);
				}
			}

			/********************************************/
			/* Verificando entrada do usuário (teclado) */
			/********************************************/

			if (player.getState() == ACTIVE) {

				if (GameLib.iskeyPressed(GameLib.KEY_UP)) player.setY(player.getY() - delta * player.getVy());
				if (GameLib.iskeyPressed(GameLib.KEY_DOWN)) player.setY(player.getY() + delta * player.getVy());
				if (GameLib.iskeyPressed(GameLib.KEY_LEFT)) player.setX(player.getX() - delta * player.getVx());
				if (GameLib.iskeyPressed(GameLib.KEY_RIGHT)) player.setX(player.getX() + delta * player.getVy());


				if (GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {

					if (currentTime > player.getNextShot()) {

						int free = findFreeIndex(projectile_states);

						if (free < projectile_states.length) {

							projectile_X[free] = player.getX();
							projectile_Y[free] = player.getY() - 2 * player.getRadius();
							projectile_VX[free] = 0.0;
							projectile_VY[free] = -1.0;
							projectile_states[free] = ACTIVE;
							player.setNextShot(currentTime + 100);
						}
					}
				}
			}

			if (GameLib.iskeyPressed(GameLib.KEY_ESCAPE)) running = false;

			/* Verificando se coordenadas do player ainda estão dentro */
			/* da tela de jogo após processar entrada do usuário.      */

			if (player.getX() < 0.0) player.setX(0);
			if (player.getX() >= GameLib.WIDTH) player.setX(GameLib.WIDTH - 1);
			if (player.getY() < 25.0) player.setY(25.0);
			if (player.getY() >= GameLib.HEIGHT) player.setY(GameLib.HEIGHT - 1);

			/*******************/
			/* Desenho da cena */
			/*******************/

			/* desenhando plano fundo distante */

			GameLib.setColor(Color.DARK_GRAY);
			background2_count += background2_speed * delta;

			for (int i = 0; i < background2_X.length; i++) {

				GameLib.fillRect(background2_X[i], (background2_Y[i] + background2_count) % GameLib.HEIGHT, 2, 2);
			}

			/* desenhando plano de fundo próximo */

			GameLib.setColor(Color.GRAY);
			background1_count += background1_speed * delta;

			for (int i = 0; i < background1_X.length; i++) {

				GameLib.fillRect(background1_X[i], (background1_Y[i] + background1_count) % GameLib.HEIGHT, 3, 3);
			}

			/* desenhando player */

			if (player.getState() == EXPLODING) {

				double alpha = (currentTime - player.getExplosion_start()) / (player.getExplosion_end() - player.getExplosion_start());
				GameLib.drawExplosion(player.getX(), player.getY(), alpha);
			} else {

				GameLib.setColor(Color.BLUE);
				GameLib.drawPlayer(player.getX(), player.getY(), player.getRadius());
			}

			/* deenhando projeteis (player) */

			for (int i = 0; i < projectile_states.length; i++) {

				if (projectile_states[i] == ACTIVE) {

					GameLib.setColor(Color.GREEN);
					GameLib.drawLine(projectile_X[i], projectile_Y[i] - 5, projectile_X[i], projectile_Y[i] + 5);
					GameLib.drawLine(projectile_X[i] - 1, projectile_Y[i] - 3, projectile_X[i] - 1, projectile_Y[i] + 3);
					GameLib.drawLine(projectile_X[i] + 1, projectile_Y[i] - 3, projectile_X[i] + 1, projectile_Y[i] + 3);
				}
			}

			/* desenhando projeteis (inimigos) */

			for (int i = 0; i < e_projectile_states.length; i++) {

				if (e_projectile_states[i] == ACTIVE) {

					GameLib.setColor(Color.RED);
					GameLib.drawCircle(e_projectile_X[i], e_projectile_Y[i], e_projectile_radius);
				}
			}

			/* desenhando inimigos (tipo 1 e 2) */

			for (Enemy enemy : allEnemies) {
				if (enemy instanceof Enemy1) {
					Enemy1 e1 = (Enemy1) enemy;

					if (e1.getState() == EXPLODING) {

						double alpha = (currentTime - e1.getExplosion_start()) / (e1.getExplosion_end() - e1.getExplosion_start());
						GameLib.drawExplosion(e1.getX(), e1.getY(), alpha);
					}

					if (e1.getState() == ACTIVE) {

						GameLib.setColor(Color.CYAN);
						GameLib.drawCircle(e1.getX(), e1.getY(), e1.getRadius());
					}


				} else if (enemy instanceof Enemy2) {
					Enemy2 e2 = (Enemy2) enemy;

					if (e2.getState() == EXPLODING) {

						double alpha = (currentTime - e2.getExplosion_start()) / (e2.getExplosion_end() - e2.getExplosion_start());
						GameLib.drawExplosion(e2.getX(), e2.getY(), alpha);
					}

					if (e2.getState() == ACTIVE) {

						GameLib.setColor(Color.MAGENTA);
						GameLib.drawCircle(e2.getX(), e2.getY(), e2.getRadius());
					}

				}

			}

			/* chamada a display() da classe GameLib atualiza o desenho exibido pela interface do jogo. */

			GameLib.display();

			/* faz uma pausa de modo que cada execução do laço do main loop demore aproximadamente 3 ms. */

			busyWait(currentTime + 3);
		}

		System.exit(0);
	}
}