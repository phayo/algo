package algo;

public class NailingPlanks {
    public static int solution(int[] A, int[] B, int[] C)
    {
        int minNailIndex = 1;
        int maxNailIndex = C.length;
        int result = -1;
        while (minNailIndex <= maxNailIndex)
        {
            int mid = (maxNailIndex + minNailIndex) / 2;
            if (allNailed(mid, A, B, C))
            {
                maxNailIndex = mid - 1;
                result = mid;
            }
            else
            {
                minNailIndex = mid + 1;
            }
        }

        return result;
    }

    private static boolean allNailed(int nailsCount, int[] A, int[] B, int[] C)
    {
        int[] nailsMarked = new int[2 * C.length + 1];
        for (int i = 0; i < nailsCount; i++)
        {
            nailsMarked[C[i]] = 1;
        }

        // generate prefix sums, so we can make queries for the plank range
        // for example plank(1,5) is nailed when the number of nails at start and end differs, 
        // i.e. there is a nail in this range
        for (int i = 1; i < nailsMarked.length; i++)
        {
            nailsMarked[i] += nailsMarked[i - 1];
        }

        boolean allNailed = true;
        for (int i = 0; i < A.length && allNailed; i++)
        {
            allNailed = nailsMarked[B[i]] - nailsMarked[A[i] - 1] > 0;
        }

        return allNailed;
    }
}
