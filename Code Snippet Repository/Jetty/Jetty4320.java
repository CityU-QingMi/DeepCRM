    public String generateContent(int length)
    {
        StringBuilder builder = new StringBuilder();
        do
        {
            builder.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In quis felis nunc.\n");
            builder.append("Quisque suscipit mauris et ante auctor ornare rhoncus lacus aliquet. Pellentesque\n");
            builder.append("habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.\n");
            builder.append("Vestibulum sit amet felis augue, vel convallis dolor. Cras accumsan vehicula diam\n");
            builder.append("at faucibus. Etiam in urna turpis, sed congue mi. Morbi et lorem eros. Donec vulputate\n");
            builder.append("velit in risus suscipit lobortis. Aliquam id urna orci, nec sollicitudin ipsum.\n");
            builder.append("Cras a orci turpis. Donec suscipit vulputate cursus. Mauris nunc tellus, fermentum\n");
            builder.append("eu auctor ut, mollis at diam. Quisque porttitor ultrices metus, vitae tincidunt massa\n");
            builder.append("sollicitudin a. Vivamus porttitor libero eget purus hendrerit cursus. Integer aliquam\n");
            builder.append("consequat mauris quis luctus. Cras enim nibh, dignissim eu faucibus ac, mollis nec neque.\n");
            builder.append("Aliquam purus mauris, consectetur nec convallis lacinia, porta sed ante. Suspendisse\n");
            builder.append("et cursus magna. Donec orci enim, molestie a lobortis eu, imperdiet vitae neque.\n");
        }
        while (builder.length() < length);

        // Make sure we are exactly at requested length. (truncate the extra)
        if (builder.length() > length)
        {
            builder.setLength(length);
        }

        return builder.toString();
    }
