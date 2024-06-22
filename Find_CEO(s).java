# 1. Find CEO(s)
Problem Statement:
  Given number of employee-employee relations i j, where employee i reports to employee j, find names of employee who are eligible for CEo position.A CEO of a company in one who is top in hierarchy.
  Print all such ceo(s)
  Employee name in given in uppercase alphabets (A to Z)


Test Case #1:
  A C
  B C
  C F
  D E
  E F
  F F

  output : F

Test case #2:
  A A
  B B
  C C

  output: A B C


Solution in java:
import java.util.*;

public class Solution {

    public void assignAndPrint(String[][] t) {
        Map<String, List<String>> d = new HashMap<>();

        for (String[] pair : t) {
            String manager = pair[0];
            String employee = pair[1];

            if (!d.containsKey(manager)) {
                d.put(manager, new ArrayList<>());
            }
            if (!d.containsKey(employee)) {
                d.put(employee, new ArrayList<>(Arrays.asList(manager)));
            } else {
                d.get(employee).add(manager);
            }
        }

        Map<String, Integer> c = new HashMap<>();
        for (String manager : d.keySet()) {
            int reportCount = d.get(manager).size();
            for (String employee : d.get(manager)) {
                reportCount += d.getOrDefault(employee, new ArrayList<>()).size();
            }
            c.put(manager, reportCount);
        }

        int maxReports = Integer.MIN_VALUE;
        List<String> ceos = new ArrayList<>();
        for (String manager : c.keySet()) {
            int reports = c.get(manager);
            if (reports > maxReports) {
                maxReports = reports;
                ceos.clear();
                ceos.add(manager);
            } else if (reports == maxReports) {
                ceos.add(manager);
            }
        }

        for (String ceo : ceos) {
            System.out.print(ceo + " ");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int relation = Integer.parseInt(scanner.nextLine().trim());
        String[][] t = new String[relation][2];

        for (int i = 0; i < relation; i++) {
            String[] temp = scanner.nextLine().trim().split("\\s+");
            t[i][0] = temp[0];
            t[i][1] = temp[1];
        }

        Solution solution = new Solution();
        solution.assignAndPrint(t);

        scanner.close();
    }
}




Solution in C++:
#include <iostream>
#include <vector>
#include <unordered_map>
#include <algorithm>

using namespace std;

class Solution {
public:
    void assignAndPrint(vector<vector<string>>& t) {
        unordered_map<string, vector<string>> d;

        // Populate the map d
        for (auto& pair : t) {
            string manager = pair[0];
            string employee = pair[1];

            if (d.find(manager) == d.end()) {
                d[manager] = vector<string>();
            }
            if (d.find(employee) == d.end()) {
                d[employee] = vector<string>{manager};
            } else {
                d[employee].push_back(manager);
            }
        }

        // Calculate the number of reports for each manager
        unordered_map<string, int> c;
        for (auto& entry : d) {
            string manager = entry.first;
            int reportCount = d[manager].size();
            for (auto& employee : d[manager]) {
                reportCount += d.count(employee) ? d[employee].size() : 0;
            }
            c[manager] = reportCount;
        }

        // Find the manager(s) with the maximum reports
        int maxReports = INT_MIN;
        vector<string> ceos;
        for (auto& entry : c) {
            string manager = entry.first;
            int reports = entry.second;
            if (reports > maxReports) {
                maxReports = reports;
                ceos.clear();
                ceos.push_back(manager);
            } else if (reports == maxReports) {
                ceos.push_back(manager);
            }
        }

        // Output the CEO(s)
        for (auto& ceo : ceos) {
            cout << ceo << " ";
        }
    }
};

int main() {
    int relation;
    cin >> relation;
    vector<vector<string>> t(relation, vector<string>(2));

    // Read the input
    for (int i = 0; i < relation; ++i) {
        cin >> t[i][0] >> t[i][1];
    }

    // Create solution object and invoke the method
    Solution solution;
    solution.assignAndPrint(t);

    return 0;
}
