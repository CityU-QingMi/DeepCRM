	public Vocab getRandomVocab() {
		if (vocabs == null) {
			throw new HangmanException(HangmanException.Type.valueOf("NO_VOCAB_SOURCE"), "No vocab source");
		}
		if (vocabs.size() <= 0) {
			throw new HangmanException(HangmanException.Type.valueOf("NO_VOCAB"), "No vocab");
		}
		long vocabIndex = Math.round((Math.random() * (double) prop.size()));
		vocabIndex = vocabIndex == vocabs.size() ? vocabs.size() - 1 : vocabIndex;
		return vocabs.get((int) vocabIndex);
	}
