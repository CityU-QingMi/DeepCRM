		@Override
		protected List<Item> buildFeedItems(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
			List<Item> items = new ArrayList<>();
			for (String name : model.keySet()) {
				Item item = new Item();
				item.setTitle(name);
				Description description = new Description();
				description.setValue((String) model.get(name));
				item.setDescription(description);
				items.add(item);
			}
			return items;
		}
