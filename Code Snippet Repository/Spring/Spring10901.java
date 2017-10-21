		@Override
		protected FileUpload newFileUpload(FileItemFactory fileItemFactory) {
			return new ServletFileUpload() {
				@Override
				public List<FileItem> parseRequest(HttpServletRequest request) {
					if (request instanceof MultipartHttpServletRequest) {
						throw new IllegalStateException("Already a multipart request");
					}
					List<FileItem> fileItems = new ArrayList<>();
					MockFileItem fileItem1 = new MockFileItem(
						"field1", "type1", empty ? "" : "field1.txt", empty ? "" : "text1");
					MockFileItem fileItem1x = new MockFileItem(
						"field1", "type1", empty ? "" : "field1.txt", empty ? "" : "text1");
					MockFileItem fileItem2 = new MockFileItem(
						"field2", "type2", empty ? "" : "C:\\mypath/field2.txt", empty ? "" : "text2");
					MockFileItem fileItem2x = new MockFileItem(
						"field2x", "type2", empty ? "" : "C:/mypath\\field2x.txt", empty ? "" : "text2");
					MockFileItem fileItem3 = new MockFileItem("field3", null, null, "value3");
					MockFileItem fileItem4 = new MockFileItem("field4", "text/html; charset=iso-8859-1", null, "value4");
					MockFileItem fileItem5 = new MockFileItem("field4", null, null, "value5");
					fileItems.add(fileItem1);
					fileItems.add(fileItem1x);
					fileItems.add(fileItem2);
					fileItems.add(fileItem2x);
					fileItems.add(fileItem3);
					fileItems.add(fileItem4);
					fileItems.add(fileItem5);
					return fileItems;
				}
			};
		}
