	@Test
	public void testXlsxView() throws Exception {
		View excelView = new AbstractXlsxView() {
			@Override
			protected void buildExcelDocument(Map<String, Object> model, Workbook workbook,
					HttpServletRequest request, HttpServletResponse response) throws Exception {
				Sheet sheet = workbook.createSheet("Test Sheet");
				Row row = sheet.createRow(0);
				Cell cell = row.createCell(0);
				cell.setCellValue("Test Value");
			}
		};

		excelView.render(new HashMap<>(), request, response);

		Workbook wb = new XSSFWorkbook(new ByteArrayInputStream(response.getContentAsByteArray()));
		assertEquals("Test Sheet", wb.getSheetName(0));
		Sheet sheet = wb.getSheet("Test Sheet");
		Row row = sheet.getRow(0);
		Cell cell = row.getCell(0);
		assertEquals("Test Value", cell.getStringCellValue());
	}
