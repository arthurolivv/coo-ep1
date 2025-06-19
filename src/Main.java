import GameEngine.Interfaces.GameObject;
import GameEngine.Objects.Enemys.Enemy1;
import GameEngine.Objects.Enemys.Enemy2;
import GameEngine.Objects.Enemys.EnemyGeneric;
import GameEngine.Objects.Player;
import libs.GameLib;
import states.GameStates;
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
	
//	public static final int INACTIVE = 0;
//	public static final int ACTIVE = 1;				REFEITO A PARTIR DA CLASSE ENUM GAMESTATES
//	public static final int EXPLODING = 2;

	public static final int INACTIVE = GameStates.INACTIVE.getValue();
	public static final int ACTIVE = GameStates.ACTIVE.getValue();
	public static final int EXPLODING = GameStates.EXPLODING.getValue();


	/* Espera, sem fazer nada, até que o instante de tempo atual seja */
	/* maior ou igual ao instante especificado no parâmetro "time.    */
	
	public static void busyWait(long time){
		
		while(System.currentTimeMillis() < time) Thread.yield();
	}
	
	/* Encontra e devolve o primeiro índice do  */
	/* array referente a uma posição "inativa". */
	
	public static int findFreeIndex(int [] stateArray){
		
		int i;
		
		for(i = 0; i < stateArray.length; i++){
			
			if(stateArray[i] == INACTIVE) break;
		}
		
		return i;
	}
	
	/* Encontra e devolve o conjunto de índices (a quantidade */
	/* de índices é defnida através do parâmetro "amount") do */
	/* array referente a posições "inativas".                 */ 

	public static int [] findFreeIndex(int [] stateArray, int amount){

		int i, k;
		int [] freeArray = new int[amount];

		for(i = 0; i < freeArray.length; i++) freeArray[i] = stateArray.length; 
		
		for(i = 0, k = 0; i < stateArray.length && k < amount; i++){
				
			if(stateArray[i] == INACTIVE) { 
				
				freeArray[k] = i; 
				k++;
			}
		}
		
		return freeArray;
	}
	
	/* Método principal */
	
	public static void main(String [] args){

		/* Indica que o jogo está em execução */

		boolean running = true;

		/* variáveis usadas no controle de tempo efetuado no main loop */
		
		long delta;
		long currentTime = System.currentTimeMillis();

		// Constantes de configuração
		double DEFAULT_X = GameLib.WIDTH / 2.0;
		double DEFAULT_Y = GameLib.HEIGHT * 0.90;
		double DEFAULT_VELOCITY = 0.25;
		double DEFAULT_RADIUS = 12.0;
		long SHOT_COOLDOWN = 100; // tempo entre tiros em ms

		/* variáveis do player */

		Player player = new Player(
				10,
				5.0,
				ACTIVE,
				GameLib.WIDTH / 2.0,
				GameLib.HEIGHT * 0.90,
				0.25,
				0.25,
				12.0,
				0,
				0,
				System.currentTimeMillis()
		);

		/* variáveis dos inimigos tipo 1 */

		List<EnemyGeneric> enemy1 = new ArrayList<>();

		int enemy1Count = 10;

		for(int i = 0; i < enemy1Count; i++) {
			enemy1.add(new Enemy1(200, 2.0, 9.0, currentTime + 2000));
		}

		/* variáveis dos inimigos tipo 2 */

		List<EnemyGeneric> enemy2 = new ArrayList<>();

		int enemy2Count = 10;

		for (int i = 0; i < enemy2Count; i++) {
			enemy2.add(new Enemy2(200, 2.0, 12.0, currentTime + 7000, GameLib.WIDTH * 0.20, 0));
		}
		
		/* estrelas que formam o fundo de primeiro plano */
		
		double [] background1_X = new double[20];
		double [] background1_Y = new double[20];
		double background1_speed = 0.070;
		double background1_count = 0.0;
		
		/* estrelas que formam o fundo de segundo plano */
		
		double [] background2_X = new double[50];
		double [] background2_Y = new double[50];
		double background2_speed = 0.045;
		double background2_count = 0.0;
		
		/* inicializações */

		player.initializeProjectiles();

		for(int i = 0; i < enemy1Count; i++) enemy1.get(i).initializeProjectiles();
		for(int i = 0; i < enemy2Count; i++) enemy2.get(i).initializeProjectiles();

		for(int i = 0; i < enemy1Count; i++) enemy1.get(i).setState(INACTIVE);
		for(int i = 0; i < enemy2Count; i++) enemy2.get(i).setState(INACTIVE);
		
		for(int i = 0; i < background1_X.length; i++){
			
			background1_X[i] = Math.random() * GameLib.WIDTH;
			background1_Y[i] = Math.random() * GameLib.HEIGHT;
		}
		
		for(int i = 0; i < background2_X.length; i++){
			
			background2_X[i] = Math.random() * GameLib.WIDTH;
			background2_Y[i] = Math.random() * GameLib.HEIGHT;
		}
						
		/* iniciado interface gráfica */
		
		GameLib.initGraphics();
		//libs.GameLib.initGraphics_SAFE_MODE();  // chame esta versão do método caso nada seja desenhado na janela do jogo.
		
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
		
		while(running) {

			/* Usada para atualizar o estado dos elementos do jogo    */
			/* (player, projéteis e inimigos) "delta" indica quantos  */
			/* ms se passaram desde a última atualização.             */

			delta = System.currentTimeMillis() - currentTime;

			/* Já a variável "currentTime" nos dá o timestamp atual.  */

			currentTime = System.currentTimeMillis();

			/***************************/
			/* Verificação de colisões */
			/***************************/

			if (player.checkState(ACTIVE)) {

				/* colisões player - projeteis (inimigo) e inimigo adaptado 2*/

				for (EnemyGeneric enemy : enemy1) {
					player.collide(enemy, currentTime);
				}

				for (EnemyGeneric enemy : enemy2) {
					player.collide(enemy, currentTime);
				}


				/* colisões projeteis (player) - inimigos Adaptado 2*/

				for (EnemyGeneric enemy : enemy1) {
					enemy.collide(player, currentTime);
				}

				for (EnemyGeneric enemy : enemy2) {
					enemy.collide(player, currentTime);
				}

				/***************************/
				/* Atualizações de estados */
				/***************************/

				/* projeteis (player)*/
				player.updateStateProjectile(delta);

				/* projeteis (inimigos)*/

				// Atualiza projéteis dos inimigos tipo 1


				for (EnemyGeneric enemy : enemy1) {
					enemy.updateProjectiles(delta);
				}

				for (EnemyGeneric enemy : enemy2) {
					enemy.updateProjectiles(delta);
				}

				for (EnemyGeneric e : enemy1) {

					e.updateExplosion(currentTime);


					if (e.checkState(ACTIVE)) {

						/* verificando se inimigo saiu da tela */
						if (e.getY() > GameLib.HEIGHT + 10) {

							e.setState(INACTIVE);
						} else {

							e.setX(e.getX() + e.getV() * Math.cos(e.getAngle()) * delta);
							e.setY(e.getY() + e.getV() * Math.sin(e.getAngle()) * delta * (-1.0));
							e.setAngle(e.getAngle() + e.getRV() * delta);


							if (e instanceof Enemy1) {
								Enemy1 e1 = (Enemy1) e;

								if (currentTime > e1.getNextShoot() && e1.getY() < player.getY()) {

									int free = findFreeIndex(e1.getProjectile_states());

									if (free < e1.getProjectile_states().length) {

										e1.setProjectile_X(e1.getX(), free);
										e1.setProjectile_Y(e1.getY(), free);
										e1.setProjectile_VX(Math.cos(e1.getAngle()) * 0.45, free);
										e1.setProjectile_VY(Math.sin(e1.getAngle()) * 0.45 * (-1.0), free);
										e1.setProjectile_State(ACTIVE, free);

										e1.setNextShoot((long) (currentTime + 200 + Math.random() * 500));
									}
								}
							}
						}
					}
				}

				/* inimigos tipo 2  Original*/

				for (EnemyGeneric e : enemy2) {
					e.updateExplosion(currentTime);

					if (e.checkState(ACTIVE)) {

						/* verificando se inimigo saiu da tela */
						if (e.getY() < -10 || e.getX() > GameLib.WIDTH + 10) {

							e.setState(INACTIVE);
						} else {

							e.setX(e.getX() + e.getV() * Math.cos(e.getAngle()) * delta);
							e.setY(e.getY() + e.getV() * Math.sin(e.getAngle()) * delta * (-1.0));
							e.setAngle(e.getAngle() + e.getRV() * delta);

							if (e instanceof Enemy2) {
								Enemy2 e2 = (Enemy2) e;

								boolean shootNow = false;
								double previousY = e2.getY();

								double threshold = GameLib.HEIGHT * 0.30;


								if (previousY < threshold && e2.getY() >= threshold) {

									if (e2.getX() < GameLib.WIDTH / 2) e2.setRV(0.003);
									else e2.setRV(-0.003);
								}

								if (e2.getRV() > 0 && Math.abs(e2.getAngle() - 3 * Math.PI) < 0.05) {

									e2.setRV(0.0);
									e2.setAngle(3 * Math.PI);
									shootNow = true;
								}

								if (e2.getRV() < 0 && Math.abs(e2.getAngle()) < 0.05) {

									e2.setRV(0.0);
									e2.setAngle(0.0);
									shootNow = true;
								}

								if (shootNow) {

									double[] angles = {Math.PI / 2 + Math.PI / 8, Math.PI / 2, Math.PI / 2 - Math.PI / 8};
									int[] freeArray = findFreeIndex(e2.getProjectile_states(), angles.length);

									for (int k = 0; k < freeArray.length; k++) {

										int free = freeArray[k];

										if (free < e2.getProjectile_states().length) {

											double a = angles[k] + Math.random() * Math.PI / 6 - Math.PI / 12;
											double vx = Math.cos(a);
											double vy = Math.sin(a);

											e2.setProjectile_X(e2.getX(), free);
											e2.setProjectile_Y(e2.getY(), free);
											e2.setProjectile_VX(vx * 0.30, free);
											e2.setProjectile_VY(vy * 0.30, free);

											e2.setProjectile_State(ACTIVE, free);
										}
									}
								}
							}
						}
					}
				}
			}
			
			/* verificando se novos inimigos (tipo 1) devem ser "lançados" */

			for (EnemyGeneric e : enemy1) {

				// Só ativa se estiver INATIVO e o tempo atual já passou do tempo mínimo desse inimigo
				if (e.getState() == INACTIVE && currentTime > e.getNextEnemy()) {

					e.setX(Math.random() * (GameLib.WIDTH - 20.0) + 10.0);
					e.setY(-10.0);
					e.setV(0.20 + Math.random() * 0.15);
					e.setAngle((3 * Math.PI) / 2);
					e.setRV(0.0);
					e.setState(ACTIVE);

					// Se for Enemy1, também inicializa o tempo do próximo tiro
					if (e instanceof Enemy1) {
						((Enemy1) e).setNextShoot(currentTime + 500);
					}

					// Atualiza o tempo mínimo para o próximo lançamento desse mesmo inimigo
					e.setNextEnemy(currentTime + 2000); // ou outro tempo desejado

					break; // ativa só um inimigo por iteração
				}
			}
			
			/* verificando se novos inimigos (tipo 2) devem ser "lançados" */


			for (EnemyGeneric e : enemy2) {

				if (e instanceof Enemy2 && e.getState() == INACTIVE && currentTime > e.getNextEnemy()) {

					Enemy2 e2 = (Enemy2) e;

					// Posiciona e ativa o inimigo
					e2.setX(e2.getSpawnX());
					e2.setY(-10.0);
					e2.setV(0.42);
					e2.setAngle((3 * Math.PI) / 2);
					e2.setRV(0.0);
					e2.setState(ACTIVE);

					// Atualiza contador
					e2.incrementCount();

					if (e2.getCount() < 10) {
						e2.setNextEnemy(currentTime + 120);
					} else {
						e2.setCount(0);
						double newSpawnX = Math.random() > 0.5 ? GameLib.WIDTH * 0.2 : GameLib.WIDTH * 0.8;
						e2.setSpawnX(newSpawnX);
						e2.setNextEnemy((long) (currentTime + 3000 + Math.random() * 3000));
					}

					break; // só ativa um por vez
				}
			}

			
			/* Verificando se a explosão do player já acabou.         */
			/* Ao final da explosão, o player volta a ser controlável */

			if(player.getState() == EXPLODING){

				if(currentTime > player.getExplosionEnd()){

					player.setState(ACTIVE);
				}
			}
			
			/********************************************/
			/* Verificando entrada do usuário (teclado) */
			/********************************************/

			if(player.checkState(ACTIVE)){

				if(GameLib.iskeyPressed(GameLib.KEY_UP)) player.setY(player.getY() - delta * player.getVY());
				if(GameLib.iskeyPressed(GameLib.KEY_DOWN)) player.setY(player.getY() + delta * player.getVY());
				if(GameLib.iskeyPressed(GameLib.KEY_LEFT)) player.setX(player.getX() - delta * player.getVX());
				if(GameLib.iskeyPressed(GameLib.KEY_RIGHT)) player.setX(player.getX() + delta * player.getVX());

				if(GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {

					if(currentTime >player.getNextShot()){

						int free = findFreeIndex(player.getProjectile_states());

						if(free < player.getProjectile_states().length){

							player.getProjectile_X()[free] = player.getX();
							player.getProjectile_Y()[free] = player.getY() - 2 * player.getRadius();
							player.getProjectile_VX()[free] = 0.0;
							player.getProjectile_VY()[free] = -1.0;
							player.getProjectile_states()[free] = ACTIVE; //ALGO DUVIDOSO PODE ESTAR ACONTECENDO AQUI
							player.setNextShot(currentTime + 100);
						}
					}
				}
			}

			if(GameLib.iskeyPressed(GameLib.KEY_ESCAPE)) running = false;

			/* Verificando se coordenadas do player ainda estão dentro */
			/* da tela de jogo após processar entrada do usuário.      */

			if(player.getX() < 0.0) player.setX(0.0);
			if(player.getX() >= GameLib.WIDTH) player.setX(GameLib.WIDTH - 1);
			if(player.getY() < 25.0) player.setY(25.0);
			if(player.getY() >= GameLib.HEIGHT) player.setX(GameLib.HEIGHT - 1);

			/*******************/
			/* Desenho da cena */
			/*******************/
			
			/* desenhando plano fundo distante */
			
			GameLib.setColor(Color.DARK_GRAY);
			background2_count += background2_speed * delta;
			
			for(int i = 0; i < background2_X.length; i++){
				
				GameLib.fillRect(background2_X[i], (background2_Y[i] + background2_count) % GameLib.HEIGHT, 2, 2);
			}
			
			/* desenhando plano de fundo próximo */
			
			GameLib.setColor(Color.GRAY);
			background1_count += background1_speed * delta;
			
			for(int i = 0; i < background1_X.length; i++){
				
				GameLib.fillRect(background1_X[i], (background1_Y[i] + background1_count) % GameLib.HEIGHT, 3, 3);
			}
						
			/* desenhando player */

			if(player.getState() == EXPLODING){

				double alpha = (currentTime - player.getExplosionStart()) / (player.getExplosionEnd() - player.getExplosionStart());
				GameLib.drawExplosion(player.getX(), player.getY(), alpha);
			}
			else{

				GameLib.setColor(Color.BLUE);
				GameLib.drawPlayer(player.getX(), player.getY(), player.getRadius());
			}
				
			/* deenhando projeteis (player) */

				for(int i = 0; i < player.getProjectile_states().length; i++){
				if(player.getProjectile_states()[i] == ACTIVE){

					GameLib.setColor(Color.GREEN);
					GameLib.drawLine(player.getProjectile_X()[i], player.getProjectile_Y()[i] - 5, player.getProjectile_X()[i], player.getProjectile_Y()[i] + 5);
					GameLib.drawLine(player.getProjectile_X()[i] - 1, player.getProjectile_Y()[i] - 3, player.getProjectile_X()[i] - 1, player.getProjectile_Y()[i] + 3);
					GameLib.drawLine(player.getProjectile_X()[i] + 1, player.getProjectile_Y()[i] - 3, player.getProjectile_X()[i] + 1, player.getProjectile_Y()[i] + 3);
				}
			}
			
			/* desenhando projeteis (inimigos) */

			/* Desenhando projéteis de todos os inimigos tipo 1 */
			for (EnemyGeneric e : enemy1) {
				int[] states = e.getProjectile_states();
				double[] xs = e.getProjectile_X();
				double[] ys = e.getProjectile_Y();
				double radius = e.getProjectile_radius();

				for (int i = 0; i < states.length; i++) {
					if (states[i] == ACTIVE) {
						GameLib.setColor(Color.RED);
						GameLib.drawCircle(xs[i], ys[i], radius);
					}
				}
			}

			/* Desenhando projéteis de todos os inimigos tipo 2 */
			for (EnemyGeneric e : enemy2) {
				int[] states = e.getProjectile_states();
				double[] xs = e.getProjectile_X();
				double[] ys = e.getProjectile_Y();
				double radius = e.getProjectile_radius();

				for (int i = 0; i < states.length; i++) {
					if (states[i] == ACTIVE) {
						GameLib.setColor(Color.RED);
						GameLib.drawCircle(xs[i], ys[i], radius);
					}
				}
			}
			
			/* desenhando inimigos (tipo 1) */

			for (EnemyGeneric e : enemy1) {

				if (e.getState() == EXPLODING) {
					double alpha = (currentTime - e.getExplosionStart()) / (e.getExplosionEnd() - e.getExplosionStart());
					GameLib.drawExplosion(e.getX(), e.getY(), alpha);
				}

				if (e.getState() == ACTIVE) {
					GameLib.setColor(Color.CYAN);
					GameLib.drawCircle(e.getX(), e.getY(), e.getRadius());
				}
			}

			
			/* desenhando inimigos (tipo 2) */

			for (EnemyGeneric e : enemy2) {

				if (e.getState() == EXPLODING) {
					double alpha = (currentTime - e.getExplosionStart()) / (e.getExplosionEnd() - e.getExplosionStart());
					GameLib.drawExplosion(e.getX(), e.getY(), alpha);
				}

				if (e.getState() == ACTIVE) {
					GameLib.setColor(Color.MAGENTA);
					GameLib.drawDiamond(e.getX(), e.getY(), e.getRadius());
				}
			}

			
			/* chamada a display() da classe libs.GameLib atualiza o desenho exibido pela interface do jogo. */
			
			GameLib.display();
			
			/* faz uma pausa de modo que cada execução do laço do main loop demore aproximadamente 3 ms. */
			
			busyWait(currentTime + 3);
		}
		
		System.exit(0);
	}
}
