	public String execute() throws Exception {
		hangman = (Hangman) session.get(HangmanConstants.HANGMAN_SESSION_KEY);

		System.out.println("\n\n\n");
		System.out.println("hangman=" + hangman);
		System.out.println("available = " + hangman.getCharactersAvailable().size());
		System.out.println("guess left=" + hangman.guessLeft());
		System.out.println("\n\n\n");

		return SUCCESS;
	}
