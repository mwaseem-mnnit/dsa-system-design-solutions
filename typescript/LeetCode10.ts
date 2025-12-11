/*
* Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
* */

const letters = [
    'a',
    'b',
    'c',
    'd',
    'e',
    'f',
    'g',
    'h',
    'i',
    'j',
    'k',
    'l',
    'm',
    'n',
    'o',
    'p',
    'q',
    'r',
    's',
    't',
    'u',
    'v',
    'w',
    'x',
    'y',
    'z',
];
const stack = [];

function searchRecursive(
    target: string,
    current: string,
    p: string,
    i: number,
): boolean {
    if (current === target) {
        return true;
    }
    if (current.length >= target.length) {
        return false;
    }
    const c = p.charAt(i);
    let result = false;
    let k = 0;
    switch (c) {
        case '*':
            while (!result && current.length + k <= target.length) {
                if (current.length + k > target.length) {
                    return result;
                }
                const newP =
                    p.substring(0, i - 1) +
                    p.charAt(i - 1).repeat(k) +
                    p.substring(i + 1);
                result =
                    result || searchRecursive(target, current, newP, i - 1);
                k++;
            }
            return result;
        case '.':
            if (p.length > i + 1 && p.charAt(i + 1) == '*') {
                return searchRecursive(target, current, p, i + 1);
            }
            for (let j = 0; j < letters.length; j++) {
                result = searchRecursive(
                    target,
                    current + letters[j],
                    p,
                    i + 1,
                );
                if (result) {
                    return true;
                }
            }
            return false;
        default:
            if (p.length < i + 1 && p.charAt(i + 1) == '*') {
                return searchRecursive(target, current, p, i + 1);
            }
            return searchRecursive(target, current + c, p, i + 1);
    }
}
function isMatch(s: string, p: string): boolean {
    return searchRecursive(s, '', p, 0);
}
console.log(isMatch('', '.*'));

console.log(isMatch('aa', 'a'));
console.log(isMatch('ab', '.*'));

console.log(isMatch('', '.*.*.*'));
console.log(isMatch('', '.'));
console.log(isMatch('aa', 'a*'));
console.log(isMatch('aa', 'aa'));
