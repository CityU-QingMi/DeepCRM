	@Test
	public void testConstantRepresentation1() {
		checkConstantMap("{f:{'a','b','c'}}", true);
		checkConstantMap("{'a':1,'b':2,'c':3,'d':4,'e':5}", true);
		checkConstantMap("{aaa:'abc'}", true);
		checkConstantMap("{:}", true);
		checkConstantMap("{a:#a,b:2,c:3}", false);
		checkConstantMap("{a:1,b:2,c:Integer.valueOf(4)}", false);
		checkConstantMap("{a:1,b:2,c:{#a}}", false);
		checkConstantMap("{#root.name:true}",false);
		checkConstantMap("{a:1,b:2,c:{d:true,e:false}}", true);
		checkConstantMap("{a:1,b:2,c:{d:{1,2,3},e:{4,5,6},f:{'a','b','c'}}}", true);
	}
