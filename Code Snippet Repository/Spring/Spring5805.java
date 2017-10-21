	@Test
	public void primitiveTypeArrayConstructors() {
		evaluateArrayBuildingExpression("new int[]{1,2,3,4}", "[1,2,3,4]");
		evaluateArrayBuildingExpression("new boolean[]{true,false,true}", "[true,false,true]");
		evaluateArrayBuildingExpression("new char[]{'a','b','c'}", "[a,b,c]");
		evaluateArrayBuildingExpression("new long[]{1,2,3,4,5}", "[1,2,3,4,5]");
		evaluateArrayBuildingExpression("new short[]{2,3,4,5,6}", "[2,3,4,5,6]");
		evaluateArrayBuildingExpression("new double[]{1d,2d,3d,4d}", "[1.0,2.0,3.0,4.0]");
		evaluateArrayBuildingExpression("new float[]{1f,2f,3f,4f}", "[1.0,2.0,3.0,4.0]");
		evaluateArrayBuildingExpression("new byte[]{1,2,3,4}", "[1,2,3,4]");
	}
