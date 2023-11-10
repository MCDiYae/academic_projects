ma_liste = ["T", "O", "A", "p", "t", "p", "l", "o", "e", "s", "t", "t", "r", "s", "t", "t", "t", "u", "m", "m", "p"]
#sans indices paire
#simple solution 
nouvelle_list = ma_liste[::2]


#avec buocle 
nouvelle_liste = []
for i in range(len(ma_liste)):
    if i % 2 == 0:
        nouvelle_liste.append(ma_liste[i])

#sans elements t
liste_sansT = []   

for element in nouvelle_liste:
    if element != "t":
        liste_sansT.append(element)

print("Liste originale:", ma_liste)
print("Nouvelle liste apres suppression des indice pair:", nouvelle_liste)
print(nouvelle_list)
print(liste_sansT)
