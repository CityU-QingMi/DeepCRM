	public Character[] inCharacters() {
		if (characters == null) {
			char[] c = vocab.toCharArray();
			characters = new Character[c.length];
			for (int a = 0; a < c.length; a++) {
				characters[a] = Character.valueOf(c[a]);
			}
		}
		return characters;
	}
