/* 
 *   created by mohdwaseem
 *   created on 27/12/24  
 *   Time: 8:08 PM
 *   To change this template use File | Settings | File and Code Templates.
*/


/**
 * Encode and Decode Strings - Leetcode 271
**/
export class LeetCode271 {

    public static encode(inputList: string[]): string {
        if (!inputList?.length) {
            return '';
        }

        return inputList.reduce((pV, cV) => {
            return `${pV}${LeetCode271.doEncode(cV)}`;
        }, '');
    }

    public static decode(input: string): string[] {
        if (!input?.length) return [];
        const result = [];
        for (let i = 0; i < input.length; ) {
            const wordLength = LeetCode271.computeWordLength(i, input);
            result.push(LeetCode271.fetchWord(
                i + String(wordLength).length + 1, wordLength, input)
            );
            i = i + String(wordLength).length + wordLength + 1;
        }
        return result;
    }

    private static doEncode(input: string): string {
        return input.length + '#' + input;
    }

    private static computeWordLength(i: number, input: string) {
        let wordLength = '';
        while(i < input.length && input.charAt(i) !== '#' ) {
            wordLength += input.charAt(i);
            i++;
        }
        return Number(wordLength);
    }

    private static fetchWord(i: number, wordLength: number, input: string) {
        return input.substr(i, wordLength);
    }
}
