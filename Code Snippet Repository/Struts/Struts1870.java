    private String encodeTextFile(String bondary, String endline, String name, String filename, String contentType, String content) {
        final StringBuilder sb = new StringBuilder(64);
        sb.append(endline);
        sb.append("--");
        sb.append(bondary);
        sb.append(endline);
        sb.append("Content-Disposition: form-data; name=\"");
        sb.append(name);
        sb.append("\"; filename=\"");
        sb.append(filename);
        sb.append(endline);
        sb.append("Content-Type: ");
        sb.append(contentType);
        sb.append(endline);
        sb.append(endline);
        sb.append(content);

        return sb.toString();
    }
