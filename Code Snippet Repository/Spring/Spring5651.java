	private void setArrayElement(TypeConverter converter, Object ctx, int idx, @Nullable Object newValue,
			Class<?> arrayComponentType) throws EvaluationException {

		if (arrayComponentType == Double.TYPE) {
			double[] array = (double[]) ctx;
			checkAccess(array.length, idx);
			array[idx] = convertValue(converter, newValue, Double.class);
		}
		else if (arrayComponentType == Float.TYPE) {
			float[] array = (float[]) ctx;
			checkAccess(array.length, idx);
			array[idx] = convertValue(converter, newValue, Float.class);
		}
		else if (arrayComponentType == Long.TYPE) {
			long[] array = (long[]) ctx;
			checkAccess(array.length, idx);
			array[idx] = convertValue(converter, newValue, Long.class);
		}
		else if (arrayComponentType == Integer.TYPE) {
			int[] array = (int[]) ctx;
			checkAccess(array.length, idx);
			array[idx] = convertValue(converter, newValue, Integer.class);
		}
		else if (arrayComponentType == Short.TYPE) {
			short[] array = (short[]) ctx;
			checkAccess(array.length, idx);
			array[idx] = convertValue(converter, newValue, Short.class);
		}
		else if (arrayComponentType == Byte.TYPE) {
			byte[] array = (byte[]) ctx;
			checkAccess(array.length, idx);
			array[idx] = convertValue(converter, newValue, Byte.class);
		}
		else if (arrayComponentType == Character.TYPE) {
			char[] array = (char[]) ctx;
			checkAccess(array.length, idx);
			array[idx] = convertValue(converter, newValue, Character.class);
		}
		else if (arrayComponentType == Boolean.TYPE) {
			boolean[] array = (boolean[]) ctx;
			checkAccess(array.length, idx);
			array[idx] = convertValue(converter, newValue, Boolean.class);
		}
		else {
			Object[] array = (Object[]) ctx;
			checkAccess(array.length, idx);
			array[idx] = convertValue(converter, newValue, arrayComponentType);
		}
	}
