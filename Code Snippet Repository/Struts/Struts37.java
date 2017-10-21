	public void guess(Character character) {
		assert (character != null);

		synchronized (charactersAvailable) {
			if (guessLeft < 0) {
				throw new HangmanException(
						HangmanException.Type.valueOf("GAME_ENDED"), "Game already eneded");
			}
			Character characterInUpperCase = Character.toUpperCase(character);
			boolean ok = charactersAvailable.remove(characterInUpperCase);
			if (ok) {
				charactersGuessed.add(characterInUpperCase);
				if (!vocab.containCharacter(characterInUpperCase)) {
					guessLeft = guessLeft - 1;
				}
			}
			if (vocab.containsAllCharacter(charactersGuessed)) {
				win = true;
			}
			System.out.println(" *********************************** " + win);
		}
	}
