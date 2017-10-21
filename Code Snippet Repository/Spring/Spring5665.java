	public static Literal getRealLiteral(String numberToken, int pos, boolean isFloat) {
		try {
			if (isFloat) {
				float value = Float.parseFloat(numberToken);
				return new FloatLiteral(numberToken, pos, value);
			}
			else {
				double value = Double.parseDouble(numberToken);
				return new RealLiteral(numberToken, pos, value);
			}
		}
		catch (NumberFormatException ex) {
			throw new InternalParseException(new SpelParseException(pos>>16, ex, SpelMessage.NOT_A_REAL, numberToken));
		}
	}
