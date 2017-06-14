package br.jgb.jogo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Inicio do jogo
 * 
 * @author Jandrei
 *
 */
public class Jogo {

	private String[] botoesSimNao = { "Sim", "N�o" };
	private String[] botaoOk = { "OK" };
	private List<Animal> animais = new ArrayList<Animal>();
	private Animal animalUsuario;
	private Animal ultimoAnimal;
	private int qtdeAcertos = 0;

	public Jogo() {
		// animais iniciais
		animais.add(new Animal("Macaco", OndeVive.OUTROS));
		animais.add(new Animal("Tubar�o", OndeVive.AGUA));

		inicializacao();
	}

	/**
	 * Inc�o das perguntas do jogo
	 */
	public void inicializacao() {

		perguntarPensarAnimal();
		perguntarOndeAnimalVive();

		// percorre animais para descobrir qual animal o usu�rio pensou
		boolean encontrou = false;
		for (Animal animal : animais) {

			if (animal.getOndeVive().equals(animalUsuario.getOndeVive())) {

				// pergunta se � o animal do usu�rio
				int resp = perguntarAnimalQuePensei(animal);

				if (resp == JOptionPane.NO_OPTION) {
					continue;
				} else if (resp == JOptionPane.YES_OPTION) {
					qtdeAcertos++;

					if (qtdeAcertos == 1) {
						JOptionPane.showMessageDialog(null, "Acertei!");
					} else {
						JOptionPane.showMessageDialog(null, "Acertei de novo!");
					}

					encontrou = true;
					break;
				}
			}
		}

		// terminou os animais e n�o acertei qual �, pergunta o nome
		if (encontrou == false) {
			novoAnimal();
		}

		// inicia novamente
		inicializacao();
	}

	/**
	 * Pede para pensar em um animal
	 */
	private void perguntarPensarAnimal() {
		if (JOptionPane.showOptionDialog(null, "Pense em um Animal", "Jogo dos animais", JOptionPane.OK_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, botaoOk, botaoOk[0]) == JOptionPane.CLOSED_OPTION) {
			System.exit(0);
		}
	}

	/**
	 * Pergunta onde o animal vive
	 */
	private void perguntarOndeAnimalVive() {
		int resp = JOptionPane.showOptionDialog(null, "Vive na �gua? ", "Confirme", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, botoesSimNao, botoesSimNao[0]);

		if (resp == JOptionPane.CLOSED_OPTION) {
			System.exit(0);
		}

		// animal que o usu�rio pensou
		OndeVive ondeVive = (resp == JOptionPane.YES_OPTION) ? OndeVive.AGUA : OndeVive.OUTROS;
		animalUsuario = new Animal(ondeVive);
	}

	/**
	 * Pergunta o nome do animal que pensei
	 * 
	 * @return
	 */
	private int perguntarAnimalQuePensei(Animal animal) {
		ultimoAnimal = animal;
		int resp = JOptionPane.showOptionDialog(null, "O animal que pensei � um(a) " + animal.getNome() + "?",
				"Confirme", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoesSimNao,
				botoesSimNao[0]);

		if (resp == JOptionPane.CLOSED_OPTION) {
			System.exit(0);
		}

		return resp;
	}

	/**
	 * Adi��o do novo animal escolhido pelo usu�rio
	 */
	private void novoAnimal() {
		String nome = JOptionPane.showInputDialog(null, "Qual o animal que voc� pensou?");
		nome = JOptionPane.showInputDialog(null,
				"Um(a) " + nome + "_____ mas um(a) " + ultimoAnimal.getNome() + " n�o.", "Complete",
				JOptionPane.PLAIN_MESSAGE);

		animalUsuario.setNome(nome);
		animais.add(animalUsuario);
	}
}