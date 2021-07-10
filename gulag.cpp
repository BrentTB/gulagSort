#include <bits/stdc++.h>
using namespace std;
#define ll long long
#define uint unsigned int
#define ull unsigned long long
#define endl "\n"
#define elif else if
#define fo(i, a, b) for (int i = a; i < (int)b; i++)
#define rfo(i, a, b) for (int i = a - 1; i >= b; i--)
#define v(i) vector<i>
#define vll vector<long long>
#define vint vector<int>
#define vstr vector<string>
#define pairll pair<long long, long long>
#define vpairll vector<pair<long long, long long>>
#define print(x) cout << x << "\n"

#define printall(x)        \
    for (auto zz : x)      \
    {                      \
        cout << zz << " "; \
    }                      \
    cout << "\n";

#define printallpair(x)            \
    for (auto zz : x)              \
    {                              \
        cout << zz.first << " ";   \
        cout << zz.second << "\n"; \
    }

#define pushall(x, n)                   \
    x.resize(n);                        \
    for (int zz = 0; zz < (int)n; zz++) \
    {                                   \
        cin >> x[zz];                   \
    }

#define pushallpair(x, n)                   \
    x.resize(n);                            \
    for (int zz = 0; zz < (int)n; zz++)     \
    {                                       \
        cin >> x[zz].first >> x[zz].second; \
    }

vint sortArray(vint &nums) // small to big
{
    // print(nums.size());

    vint sorted, unsorted;
    sorted.reserve(nums.size());
    unsorted.reserve(nums.size());

    int on = INT32_MIN;

    for (auto number : nums)
    {
        if (number >= on)
        {
            sorted.push_back(number);
            on = number;
            continue;
        }
        unsorted.push_back(number);
    }

    if (1.0 * sorted.size() < unsorted.size() / 50.0) // if the numbers are in decreasing order
    {

        sorted.clear();
        unsorted.clear();
        sorted.reserve(nums.size());
        unsorted.reserve(nums.size());

        int on = INT32_MIN;

        rfo(i, nums.size(), 0)
        {
            if (nums[i] >= on)
            {
                sorted.push_back(nums[i]);
                on = nums[i];
                continue;
            }
            unsorted.push_back(nums[i]);
        }
    }

    if (unsorted.size() == 0)
    {
        return sorted;
    }

    unsorted = sortArray(unsorted);

    sorted.push_back(INT32_MAX);
    unsorted.push_back(INT32_MAX);

    int sI = 0, usI = 0;

    vint ret;
    ret.reserve(sorted.size() + unsorted.size());

    while (sorted[sI] != INT32_MAX || unsorted[usI] != INT32_MAX)
    {

        if (sorted[sI] <= unsorted[usI])
        {
            ret.push_back(sorted[sI++]);
            continue;
        }
        ret.push_back(unsorted[usI++]);
    }

    return ret;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    vint nums;

    freopen("inp.txt", "r", stdin);

    string a;

    while (getline(cin, a, ','))
    {
        nums.push_back(stoi(a));
    }

    nums = sortArray(nums);

    cout << nums[1] << endl;
}
