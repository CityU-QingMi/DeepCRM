	public Hangman(Vocab vocab) {
		// Arrays.asList(...) returns List that doesn't support remove(), hence
		// we wrap it with an ArrayList to avoid UnsupportedOperationException
		// when doing a remove()
		charactersAvailable = new ArrayList<Character>(Arrays.asList(
				new Character[]{
						Character.valueOf('A'), Character.valueOf('B'), Character.valueOf('C'),
						Character.valueOf('D'), Character.valueOf('E'), Character.valueOf('F'),
						Character.valueOf('G'), Character.valueOf('H'), Character.valueOf('I'),
						Character.valueOf('J'), Character.valueOf('K'), Character.valueOf('L'),
						Character.valueOf('M'), Character.valueOf('N'), Character.valueOf('O'),
						Character.valueOf('P'), Character.valueOf('Q'), Character.valueOf('R'),
						Character.valueOf('S'), Character.valueOf('T'), Character.valueOf('U'),
						Character.valueOf('V'), Character.valueOf('W'), Character.valueOf('X'),
						Character.valueOf('Y'), Character.valueOf('Z')
				}));
		charactersGuessed = new ArrayList<Character>();
		this.vocab = vocab;
	}
