		@Override
		protected List<Entry> buildFeedEntries(Map model, HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			List<Entry> entries = new ArrayList<>();
			for (Iterator iterator = model.keySet().iterator(); iterator.hasNext();) {
				String name = (String) iterator.next();
				Entry entry = new Entry();
				entry.setTitle(name);
				Content content = new Content();
				content.setValue((String) model.get(name));
				entry.setSummary(content);
				entries.add(entry);
			}
			return entries;
		}
