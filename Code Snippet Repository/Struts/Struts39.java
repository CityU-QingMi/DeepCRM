	protected List<Vocab> readVocab(Properties prop) {
		List<Vocab> vocabList = new ArrayList<Vocab>();

		for (Map.Entry e : prop.entrySet()) {
			String vocab = (String) e.getKey();
			String hint = (String) e.getValue();

			vocabList.add(new Vocab(vocab, hint));
		}
		return vocabList;
	}
