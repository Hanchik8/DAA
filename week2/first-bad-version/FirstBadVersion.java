class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int firstVersion = 1;
        int lastVersion = n;

        while (firstVersion < lastVersion) {
            int middleVersion = firstVersion + (lastVersion - firstVersion) / 2;

            if (isBadVersion(middleVersion)) {
                lastVersion = middleVersion;
            } else {
                firstVersion = middleVersion + 1;
            }
        }

        return firstVersion;
    }
}
