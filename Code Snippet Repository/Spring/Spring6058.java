		public ContextObject() {
			firstContext.put("shouldBeFirst", "first");
			secondContext.put("shouldBeFirst", "second");
			thirdContext.put("shouldBeFirst", "third");
			fourthContext.put("shouldBeFirst", "fourth");

			secondContext.put("shouldBeSecond", "second");
			thirdContext.put("shouldBeSecond", "third");
			fourthContext.put("shouldBeSecond", "fourth");

			thirdContext.put("shouldBeThird", "third");
			fourthContext.put("shouldBeThird", "fourth");

			fourthContext.put("shouldBeFourth", "fourth");
		}
