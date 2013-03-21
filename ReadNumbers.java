/* 
Author: Pascal Bruno (pascal@pascalbruno.com)
Date: 03/09/13
Description: This class accepts a string of integers and
             converts the integer to it's alphabetic version
*/

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

class ReadNumbers
    {
        private Queue<Integer> digitQueue = new LinkedList<Integer>();
        private Queue<String> alphaQueue = new LinkedList<String>();
        private String numberToRead;
        private String output = "";
        private int len;
        private int numOne = -1;
        private int numTwo = -1;
        private int numThree = -1;

        String[] basicNames = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] teenNames = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] tyNames = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninty"};
        String[] largeNames = {"thousand", "million", "billion", "trillion", "quadrillion", "quintillion", "sextillion", "septillion",
                               "octillion", "nonillion", "decillion", "undecillion", "duodecillion", "tredecillion", "quattuordecillion",
                               "quindecillion"};

        public ReadNumbers(String userInput)
            {
                numberToRead = userInput;
                len = numberToRead.length();
                initialize();
            }

        public void initialize()
            {
                for (int i = 0; i < len; i++)
                    {
                        String digit = numberToRead.substring(0,1);
                        numberToRead = numberToRead.substring(1);
                        int toAdd = Integer.parseInt(digit);
                        digitQueue.add(toAdd);
                    }
                convertNumberToAlphabetic();
            }

        public void convertNumberToAlphabetic()
            {
                while (!digitQueue.isEmpty())
                    {
                        int digitQueueSize = digitQueue.size();
                        int toDeQueue = digitQueueSize % 3;
                        int largeNumber = digitQueueSize / 3;

                        if (toDeQueue == 1)
                            {
                                numOne = digitQueue.poll();
                                if (numOne >= 1 && digitQueue.isEmpty())
                                    alphaQueue.add(" " + basicNames[numOne - 1]);
                                else if (numOne == 0 && digitQueue.isEmpty())
                                    alphaQueue.add(" Zero");
                                else
                                    alphaQueue.add(" " + basicNames[numOne - 1]);

                                if (largeNumber >= 1)
                                    {
                                        if (!digitQueue.isEmpty())
                                            {
                                                alphaQueue.add(" " + largeNames[(largeNumber - 1)]);
                                            }
                                    }
                            }

                        if (toDeQueue == 2)
                            {
                                numOne = digitQueue.poll();
                                numTwo = digitQueue.poll();

                                if (largeNumber >= 1 && digitQueue.size() == 0)
                                    alphaQueue.add(" " + largeNames[(largeNumber - 1)]);
                                
                                if (numOne == 1)
                                    {
                                        alphaQueue.add(" " + teenNames[numTwo]);
                                    }
                                else
                                    {
                                        if (numOne >= 1)
                                            {
                                                alphaQueue.add(" " + tyNames[(numOne - 2)]);
                                                if (numTwo >= 1)
                                                    {
                                                        alphaQueue.add(" " + basicNames[(numTwo - 1)]);
                                                    }
                                            }
                                    }

                                if (largeNumber >= 1)
                                    {
                                        if (!digitQueue.isEmpty())
                                            {
                                                alphaQueue.add(" " + largeNames[(largeNumber - 1)]);
                                            }
                                    }
                            }

                        if (toDeQueue == 0)
                            {
                                numOne = digitQueue.poll();
                                numTwo = digitQueue.poll();
                                numThree = digitQueue.poll();
                                
                                if (numOne >= 1)
                                    {
                                        alphaQueue.add(" " + basicNames[numOne - 1] + " hundred");

                                        if (numTwo > 1)
                                            {
                                                alphaQueue.add(" " + tyNames[(numTwo) - 2]);

                                                if (numThree >= 1)
                                                    {
                                                        alphaQueue.add(" " + basicNames[(numThree - 1)]);
                                                    }
                                            }
                                        if (numTwo == 1)
                                            {
                                                alphaQueue.add(" " + teenNames[(numThree)]);
                                            }
                                        if (numTwo == 0)
                                            {
                                                if (numThree >= 1)
                                                    {
                                                        alphaQueue.add(" " + basicNames[(numThree - 1)]);
                                                    }
                                            }
                                    }
                                else
                                    {
                                        if (numTwo > 1)
                                            {
                                                alphaQueue.add(" " + tyNames[(numTwo - 2)]);

                                                if (numThree >= 1)
                                                    {
                                                        alphaQueue.add(" " + basicNames[(numThree - 1)]);
                                                    }
                                            }
                                        if (numTwo == 1)
                                            {
                                                alphaQueue.add(" " + teenNames[(numThree)]);
                                            }
                                        if (numTwo == 0)
                                            {
                                                if (numThree >= 1)
                                                    {
                                                        alphaQueue.add(" " + basicNames[(numThree - 1)]);
                                                    }
                                            }
                                    }

                                    if (largeNumber >= 1)
                                        {
                                            if (!digitQueue.isEmpty())
                                                {
                                                    if (numOne != 0 || numTwo != 0 || numThree != 0)
                                                        alphaQueue.add(" " + largeNames[(largeNumber - 2)]);
                                                }
                                        }
                            }
                    }
                queueToString();
            }

        public void queueToString()
            {
                while (!alphaQueue.isEmpty())
                    {
                        output += alphaQueue.poll();
                    }
                fixFirstCharacter();
            }

        public void fixFirstCharacter()
            {
                String letterToFix = output.substring(1, 2);
                letterToFix = letterToFix.toUpperCase();
                output = letterToFix + output.substring(2);
            }

        public void readNum()
            {
                System.out.println("--> " + output + "\n");
            }

        public String getOutput()
            {
                return output;
            }
    }