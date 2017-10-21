	public static char toPrimitiveTargetDesc(String descriptor) {
		if (descriptor.length() == 1) {
			return descriptor.charAt(0);
		}
		else if (descriptor.equals("Ljava/lang/Boolean")) {
			return 'Z';
		}
		else if (descriptor.equals("Ljava/lang/Byte")) {
			return 'B';
		}
		else if (descriptor.equals("Ljava/lang/Character")) {
			return 'C';
		}
		else if (descriptor.equals("Ljava/lang/Double")) {
			return 'D';
		}
		else if (descriptor.equals("Ljava/lang/Float")) {
			return 'F';
		}
		else if (descriptor.equals("Ljava/lang/Integer")) {
			return 'I';
		}
		else if (descriptor.equals("Ljava/lang/Long")) {
			return 'J';
		}
		else if (descriptor.equals("Ljava/lang/Short")) {
			return 'S';
		}
		else {
			throw new IllegalStateException("No primitive for '" + descriptor + "'");
		}
	}
