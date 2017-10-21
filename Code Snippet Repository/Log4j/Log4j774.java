    protected SortedMap<Integer, Path> getEligibleFiles(final String path, final String logfilePattern, final boolean isAscending) {
        final TreeMap<Integer, Path> eligibleFiles = new TreeMap<>();
        final File file = new File(path);
        File parent = file.getParentFile();
        if (parent == null) {
            parent = new File(".");
        } else {
            parent.mkdirs();
        }
        if (!logfilePattern.contains("%i")) {
            return eligibleFiles;
        }
        final Path dir = parent.toPath();
        String fileName = file.getName();
        final int suffixLength = suffixLength(fileName);
        if (suffixLength > 0) {
            fileName = fileName.substring(0, fileName.length() - suffixLength) + ".*";
        }
        final String filePattern = fileName.replace(NotANumber.VALUE, "(\\d+)");
        final Pattern pattern = Pattern.compile(filePattern);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (final Path entry: stream) {
                final Matcher matcher = pattern.matcher(entry.toFile().getName());
                if (matcher.matches()) {
                    final Integer index = Integer.parseInt(matcher.group(1));
                    eligibleFiles.put(index, entry);
                }
            }
        } catch (final IOException ioe) {
            throw new LoggingException("Error reading folder " + dir + " " + ioe.getMessage(), ioe);
        }
        return isAscending? eligibleFiles : eligibleFiles.descendingMap();
    }
