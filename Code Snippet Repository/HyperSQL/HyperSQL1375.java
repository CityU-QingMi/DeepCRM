        public TarEntrySupplicant makeXentry()
                throws IOException, TarMalformatException {
            PIFGenerator pif = new PIFGenerator(new File(path));

            pif.addRecord("size", dataSize);

/**/
/**/
/**/
/**/
/**/
/**/
            return new TarEntrySupplicant(
                pif.getName(), new ByteArrayInputStream(pif.toByteArray()),
                pif.size(), 'x', tarStream);
        }
