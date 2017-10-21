    static void fillPath(String path, String name,
                         PreparedStatement prep) throws SQLException {

        File f = new File(path);

        if (f.isFile()) {

            // Clear all Parameters of the PreparedStatement
            prep.clearParameters();

            // Fill the first parameter: Path
            prep.setString(1, path);

            // Fill the second parameter: Name
            prep.setString(2, name);

            // Its a file: add it to the table
            prep.execute();
        } else if (f.isDirectory()) {
            if (!path.endsWith(File.separator)) {
                path += File.separator;
            }

            String[] list = f.list();

            // Process all files recursivly
            for (int i = 0; (list != null) && (i < list.length); i++) {
                fillPath(path + list[i], list[i], prep);
            }
        }
    }
