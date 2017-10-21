    private void doWriteData(String[] values) throws IOException {

        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                this.writer.write(";");
            }

            if (values[i] != null) {
                this.writer.write("\"");
                this.writer.write(this.toCsvValue(values[i]));
                this.writer.write("\"");
            }
        }

        this.writer.write(newline);

        this.nbrRows++;
    }
