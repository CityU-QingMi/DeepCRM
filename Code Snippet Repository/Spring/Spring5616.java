	public static int arrayCodeFor(String arraytype) {
		switch (arraytype.charAt(0)) {
			case 'I': return T_INT;
			case 'J': return T_LONG;
			case 'F': return T_FLOAT;
			case 'D': return T_DOUBLE;
			case 'B': return T_BYTE;
			case 'C': return T_CHAR;
			case 'S': return T_SHORT;
			case 'Z': return T_BOOLEAN;
			default:
				throw new IllegalArgumentException("Unexpected arraytype "+arraytype.charAt(0));
		}
	}
