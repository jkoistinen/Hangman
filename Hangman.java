import java.util.Scanner;

public class Hangman {

  public static boolean validateChar(String guessedChar){
    if (Character.isDigit(guessedChar.charAt(0))) {
      System.out.println("Inga siffror!");
      return false;
    } else if (guessedChar.length() != 1) {
      System.out.println("Endast en bokstav!");
      return false;
    }
      return true;
  }

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System. in );
		String secretword = "nisse".toUpperCase();
		//Missade gissningar läggs till i denna sträng
		String guessedChars = "Du har gissat på:";
		String secretword_blank = secretword.toLowerCase().replaceAll(".", "_");
		System.out.println(secretword_blank.replaceAll(".(?!$)", "$0 "));
		int guess = 0;
		int limitguess = 10;
		String guessedChar = "";

		while (guess < limitguess) {
			//kontrollera om du har vunnit, därefter bryt ut ur while loopen
			if (!secretword_blank.contains("_")) {
				System.out.println("Grattis du slipper bli hängd!");
				break;
			}

			System.out.println("Gissa en bokstav ?");
      //Ta in input ifrån användare och validera (längden får inte överstiga 1 och det får inte vara en siffra)
			guessedChar = keyboard.next().trim().toUpperCase();
      if(validateChar(guessedChar)){
				if (secretword.contains(guessedChar)) {
					//Iterera över secretword och markera där bokstaven finns
					for (int i = 0; i < secretword.length(); i++) {
						String secretword_char = Character.toString(secretword.charAt(i));
						if (secretword_char.equals(guessedChar)) {
							// convert to chararray
							char c = guessedChar.charAt(0);
							char[] secretword_blank_chars = secretword_blank.toCharArray();
							secretword_blank_chars[i] = c;
							secretword_blank = String.valueOf(secretword_blank_chars) + " ";
						}
					}
				} else {
					//lägg till i guessedChars om det är fel bokstav och räkna upp (+1) antal gissningar
					guessedChars = guessedChars + guessedChar + " ";
					guess = guess + 1;
				}
      }
				int guessesleft = (limitguess - guess);
				System.out.println("---------------------------------------------");
				System.out.println(secretword_blank.replaceAll(".(?!$)", "$0 "));
				System.out.println("Gissningar kvar:" + guessesleft);
				System.out.println(guessedChars);

        //kontrollera om du har förlorat
    		if (guess >= limitguess) {
    			System.out.println("Tyvärr så blir du hängd nu!");
    		}
			}
		}

	}
