    static String generateSource(final String classNameFQN, final List<LevelInfo> levels, final Type type) {
        final StringBuilder sb = new StringBuilder(10000 * levels.size());
        final int lastDot = classNameFQN.lastIndexOf('.');
        final String pkg = classNameFQN.substring(0, lastDot >= 0 ? lastDot : 0);
        if (!pkg.isEmpty()) {
            sb.append(String.format(PACKAGE_DECLARATION, pkg));
        }
        sb.append(String.format(type.imports(), ""));
        final String className = classNameFQN.substring(classNameFQN.lastIndexOf('.') + 1);
        final String javadocDescr = javadocDescription(levels);
        sb.append(String.format(type.declaration(), javadocDescr, className));
        sb.append(String.format(FQCN_FIELD, className));
        for (final LevelInfo level : levels) {
            sb.append(String.format(LEVEL_FIELD, level.name, level.name, level.intLevel));
        }
        sb.append(String.format(type.constructor(), className));
        sb.append(String.format(FACTORY_METHODS.replaceAll("CLASSNAME", className), ""));
        for (final LevelInfo level : levels) {
            final String methodName = camelCase(level.name);
            final String phase1 = METHODS.replaceAll("CUSTOM_LEVEL", level.name);
            final String phase2 = phase1.replaceAll("methodName", methodName);
            sb.append(String.format(phase2, ""));
        }

        sb.append('}');
        sb.append(System.getProperty("line.separator"));
        return sb.toString();
    }
