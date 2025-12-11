/* 
 *   created by mohdwaseem
 *   created on 16/01/25 9:37 PM
 *   To change this template use File | Settings | File and Code Templates.
*/

export class CommonAlgorithm {

    public static gcdOfTwoNumbers(num1: number, num2: number) {
        while (num1 - num2 != 0) {
            if (num1 > num2) {
                num1 -= num2;
            } else {
                num2 -= num1;
            }
        }
        return num1;
    }
}
