# FPUcalculator
This is a simple app that conuts equivalent of glucose transformed from consumed fats and proteins.
Calculation is based on methodology that assume that 10kcal from fats and proteins transofrm to equivalent of 1g of carbs in the body. 1g of fat contains 9kcal and 1 g of protein contains 4kcal, therefore the formula looks like:

(fats[g] * 9 + proteins[g] * 4) / 10

Formula for counting absorption time:

carbsEquivalent / 10 + 2

This is an alpha version of the program. Planned features: costumization of absorption time, Czech localization
