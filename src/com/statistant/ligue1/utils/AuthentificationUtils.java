package com.statistant.ligue1.utils;

import org.apache.commons.lang3.StringUtils;

import com.statistant.ligue1.controller.ExpiredMembershipException;
import com.statistant.ligue1.controller.IncoherentArgumentException;
import com.statistant.ligue1.controller.IncompatibleEmailException;
import com.statistant.ligue1.controller.UnhandledPasswordException;
import com.statistant.ligue1.dao.DatabaseConnection;
import com.statistant.ligue1.dao.NullUserException;
import com.statistant.ligue1.pojo.User;

/**
 * Class with methods used for authentification
 * 
 * @author Thomas CHARMES
 */
public class AuthentificationUtils {

	public enum Minuscules {
		a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z
	};

	public enum Majuscules {
		A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z
	};

	public static void checkSubscribtionIsActive(User user) throws ExpiredMembershipException {
		String subscribtionType = user.getSubscribtionType();
		if (Ligue1Utils.isEmpty(subscribtionType)) {
			throw new ExpiredMembershipException("L'abonnement de l'utilisateur " + user.getLogin()
					+ " est périmé. Merci de contacter l'administrateur à l'adresse mail support@statistant.fr.");
		}
		switch (subscribtionType) {
		case "EQUIPES":
			break;
		case "JOURNEES":
			String journeesSubscribed = user.getJourneesSubscribed();
			String[] tabSubscribedJournees = journeesSubscribed.split(";");
			String[] tabCurrentJournees = Ligue1Utils.getCurrentJournees();
			boolean isActive = false;
			if (tabSubscribedJournees != null && tabCurrentJournees != null) {
				for (int i = 0; i < tabSubscribedJournees.length; i++) {
					for (int j = 0; j < tabCurrentJournees.length; j++) {
						if (tabSubscribedJournees[i].equals(tabCurrentJournees[j])) {
							isActive = true;
						}
					}
				}
			}
			if (journeesSubscribed.equals("Toutes"))
				isActive = true;
			if (!isActive) {
				throw new ExpiredMembershipException("L'abonnement de l'utilisateur " + user.getLogin()
						+ " est périmé. Merci de contacter l'administrateur à l'adresse mail support@statistant.fr.");
			}
			break;
		}
	}
	
	public static void emailMatchesLogin(String email, String login)
			throws NullUserException, IncompatibleEmailException {
		User user = DatabaseConnection.getUserByLogin(login);
		if (Ligue1Utils.isEmpty(email) || !email.equals(user.getEmail())) {
			throw new IncompatibleEmailException(
					"L'adresse email saisie ne correspond pas à celle enregistrée pour cet utilisateur. Merci de réitérer la saisie.");
		}
	}

	private static boolean containSpecialChar(String input) {
		String[] tabInput = input.split("");
		for (String character : tabInput) {
			if (!isALetter(character) && !isANumber(character)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isALetter(String character) {
		Minuscules[] minuscules = Minuscules.values();
		for (Minuscules letter : minuscules) {
			if (letter.toString().equalsIgnoreCase(character)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isANumber(String character) {
		try {
			Integer.parseInt(character);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static String crypt(String password) {
		String cryptedPassword = "";
		if (!Ligue1Utils.isEmpty(password)) {
			String[] tabPassword = password.split("");
			for (String character : tabPassword) {
				if (isALetter(character)) {
					String letter = getCryptedLetter(character);
					cryptedPassword = cryptedPassword.concat(letter);
				} else {
					String number = getCryptedNumber(character);
					cryptedPassword = cryptedPassword.concat(number);
				}
			}
		}
		return StringUtils.reverse(cryptedPassword);
	}

	public static String uncrypt(String cryptedPassword) {
		String password = "";
		if (!Ligue1Utils.isEmpty(cryptedPassword)) {
			String[] tabPassword = cryptedPassword.split("");
			for (String character : tabPassword) {
				if (isALetter(character)) {
					String letter = getUncryptedLetter(character);
					password = password.concat(letter);
				} else {
					String number = getUncryptedNumber(character);
					password = password.concat(number);
				}
			}
		}
		return StringUtils.reverse(password);
	}

	private static String getUncryptedLetter(String character) {
		if (character.equals("d"))
			return "a";
		if (character.equals("e"))
			return "b";
		if (character.equals("f"))
			return "c";
		if (character.equals("g"))
			return "d";
		if (character.equals("h"))
			return "e";
		if (character.equals("i"))
			return "f";
		if (character.equals("j"))
			return "g";
		if (character.equals("k"))
			return "h";
		if (character.equals("l"))
			return "i";
		if (character.equals("m"))
			return "j";
		if (character.equals("n"))
			return "k";
		if (character.equals("o"))
			return "l";
		if (character.equals("p"))
			return "m";
		if (character.equals("q"))
			return "n";
		if (character.equals("r"))
			return "o";
		if (character.equals("s"))
			return "p";
		if (character.equals("t"))
			return "q";
		if (character.equals("u"))
			return "r";
		if (character.equals("v"))
			return "s";
		if (character.equals("w"))
			return "t";
		if (character.equals("x"))
			return "u";
		if (character.equals("y"))
			return "v";
		if (character.equals("z"))
			return "w";
		if (character.equals("a"))
			return "x";
		if (character.equals("b"))
			return "y";
		if (character.equals("c"))
			return "z";
		if (character.equals("D"))
			return "A";
		if (character.equals("E"))
			return "B";
		if (character.equals("F"))
			return "C";
		if (character.equals("G"))
			return "D";
		if (character.equals("H"))
			return "E";
		if (character.equals("I"))
			return "F";
		if (character.equals("J"))
			return "G";
		if (character.equals("K"))
			return "H";
		if (character.equals("L"))
			return "I";
		if (character.equals("M"))
			return "J";
		if (character.equals("N"))
			return "K";
		if (character.equals("O"))
			return "L";
		if (character.equals("P"))
			return "M";
		if (character.equals("Q"))
			return "N";
		if (character.equals("R"))
			return "O";
		if (character.equals("S"))
			return "P";
		if (character.equals("T"))
			return "Q";
		if (character.equals("U"))
			return "R";
		if (character.equals("V"))
			return "S";
		if (character.equals("W"))
			return "T";
		if (character.equals("X"))
			return "U";
		if (character.equals("Y"))
			return "V";
		if (character.equals("Z"))
			return "W";
		if (character.equals("A"))
			return "X";
		if (character.equals("B"))
			return "Y";
		if (character.equals("C"))
			return "Z";
		return null;
	}

	private static String getUncryptedNumber(String character) {
		if (character.equals("3"))
			return "0";
		if (character.equals("4"))
			return "1";
		if (character.equals("5"))
			return "2";
		if (character.equals("6"))
			return "3";
		if (character.equals("7"))
			return "4";
		if (character.equals("8"))
			return "5";
		if (character.equals("9"))
			return "6";
		if (character.equals("0"))
			return "7";
		if (character.equals("1"))
			return "8";
		if (character.equals("2"))
			return "9";
		return null;
	}

	private static String getCryptedNumber(String character) {
		if (character.equals("0"))
			return "3";
		if (character.equals("1"))
			return "4";
		if (character.equals("2"))
			return "5";
		if (character.equals("3"))
			return "6";
		if (character.equals("4"))
			return "7";
		if (character.equals("5"))
			return "8";
		if (character.equals("6"))
			return "9";
		if (character.equals("7"))
			return "0";
		if (character.equals("8"))
			return "1";
		if (character.equals("9"))
			return "2";
		return null;
	}

	private static String getCryptedLetter(String character) {
		if (character.equals("a"))
			return "d";
		if (character.equals("b"))
			return "e";
		if (character.equals("c"))
			return "f";
		if (character.equals("d"))
			return "g";
		if (character.equals("e"))
			return "h";
		if (character.equals("f"))
			return "i";
		if (character.equals("g"))
			return "j";
		if (character.equals("h"))
			return "k";
		if (character.equals("i"))
			return "l";
		if (character.equals("j"))
			return "m";
		if (character.equals("k"))
			return "n";
		if (character.equals("l"))
			return "o";
		if (character.equals("m"))
			return "p";
		if (character.equals("n"))
			return "q";
		if (character.equals("o"))
			return "r";
		if (character.equals("p"))
			return "s";
		if (character.equals("q"))
			return "t";
		if (character.equals("r"))
			return "u";
		if (character.equals("s"))
			return "v";
		if (character.equals("t"))
			return "w";
		if (character.equals("u"))
			return "x";
		if (character.equals("v"))
			return "y";
		if (character.equals("w"))
			return "z";
		if (character.equals("x"))
			return "a";
		if (character.equals("y"))
			return "b";
		if (character.equals("z"))
			return "c";
		if (character.equals("A"))
			return "D";
		if (character.equals("B"))
			return "E";
		if (character.equals("C"))
			return "F";
		if (character.equals("D"))
			return "G";
		if (character.equals("E"))
			return "H";
		if (character.equals("F"))
			return "I";
		if (character.equals("G"))
			return "J";
		if (character.equals("H"))
			return "K";
		if (character.equals("I"))
			return "L";
		if (character.equals("J"))
			return "M";
		if (character.equals("K"))
			return "N";
		if (character.equals("L"))
			return "O";
		if (character.equals("M"))
			return "P";
		if (character.equals("N"))
			return "Q";
		if (character.equals("O"))
			return "R";
		if (character.equals("P"))
			return "S";
		if (character.equals("Q"))
			return "T";
		if (character.equals("R"))
			return "U";
		if (character.equals("S"))
			return "V";
		if (character.equals("T"))
			return "W";
		if (character.equals("U"))
			return "X";
		if (character.equals("V"))
			return "Y";
		if (character.equals("W"))
			return "Z";
		if (character.equals("X"))
			return "A";
		if (character.equals("Y"))
			return "B";
		if (character.equals("Z"))
			return "C";
		return null;
	}

	public static boolean passwordIsModified(User user) {
		if (user != null) {
			return user.getPasswordModified() == 1;
		}
		return false;
	}

	public static void checkAreNotEmpty(String inputLogin, String inputPassword) throws IncoherentArgumentException {
		if (Ligue1Utils.isEmpty(inputLogin) || Ligue1Utils.isEmpty(inputPassword)) {
			throw new IncoherentArgumentException("Merci de renseigner les deux champs obligatoires ci-dessus.");
		}
	}

	public static void checkPasswordEqualsConfirmation(String newPassword, String confirmationPassword)
			throws IncoherentArgumentException {
		if (!newPassword.equals(confirmationPassword)) {
			throw new IncoherentArgumentException(
					"La confirmation du mot de passe n'est aps égale au mot de passe saisi. Merci de réitérer la saisie.");
		}

	}

	public static void checkPasswordMatchRequiredOptions(String newPassword) throws UnhandledPasswordException {
		if (newPassword.length() < 7) {
			throw new UnhandledPasswordException(
					"Votre mot de passe doit comporter au moins 7 caractères. Merci de réitérer la saisie.");
		}
		if (containSpecialChar(newPassword)) {
			throw new UnhandledPasswordException(
					"Votre mot de passe ne doit pas comporter de caractères spéciaux. Merci de réitérer la saisie.");
		}

	}
	
	public static void main(String[] args) {
		System.out.println(crypt("1234"));
	}
}
