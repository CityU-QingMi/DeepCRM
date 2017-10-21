		@RequestMapping("")
		public void map(@RequestParam Map<String, String> params, Writer writer) throws IOException {
			for (Iterator<Map.Entry<String, String>> it = params.entrySet().iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = it.next();
				writer.write(entry.getKey() + "=" + entry.getValue());
				if (it.hasNext()) {
					writer.write(',');
				}

			}
		}
