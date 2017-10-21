	public static void main(String args[]) throws Exception {
		Vocab v = new Vocab("JAVA", "a java word");

		List<Character> list1 = new ArrayList<Character>();
		list1.add(new Character('J'));
		list1.add(new Character('V'));

		List<Character> list2 = new ArrayList<Character>();
		list2.add(new Character('J'));
		list2.add(new Character('V'));
		list2.add(new Character('A'));

		System.out.println(v.containsAllCharacter(list1));
		System.out.println(v.containsAllCharacter(list2));

	}
