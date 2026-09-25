# First Bad Version

## 1. Problem

There are versions numbered from 1 to `n`. After the first bad version, every following version is also bad. I need to use the given `isBadVersion` method and return the number of the first bad version.

## 2. Approach

I used binary search on the version numbers.

The variables `firstVersion` and `lastVersion` show the range where the first bad version can be. I check `middleVersion`. If it is bad, the first bad version can be that version or an earlier one, so I set `lastVersion` to `middleVersion`. If it is not bad, I know that the first bad version must be after it, so I set `firstVersion` to `middleVersion + 1`.

When `firstVersion` and `lastVersion` become equal, only one possible version remains. That version is returned.

## 3. Time Complexity

**Time Complexity: O(log n)**

Each call to `isBadVersion` removes about half of the remaining versions from the search. This means the number of checks grows logarithmically instead of growing at the same rate as `n`.

## 4. Space Complexity

**Space Complexity: O(1)**

The algorithm uses only the variables `firstVersion`, `lastVersion`, and `middleVersion`. The amount of extra memory stays the same for any value of `n`.

## 5. Reflection / Improvement

The easier approach would be to start from version 1 and check every version until a bad one is found. That approach could make `n` API calls and would have O(n) time complexity.

Binary search reduces the number of calls to O(log n). This is already the efficient approach for this problem, so there is no important improvement needed for the algorithm.
