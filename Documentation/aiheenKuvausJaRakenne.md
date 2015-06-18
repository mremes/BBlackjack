# BBlackjack - Better Blackjack
Projektin tavoitteena on toteuttaa Java-kielellä viiden ruudun Blackjack-sovellus 'treenausominaisuudella'. Blackjack on korttipeli, jonka osapuolina ovat jakaja sekä pelaaja. Pelaaja pelaa jakajaa vastaan. Pelaajan tavoitteena on saada kahdella tai useammalla kortilla pelikäsi, joka voittaa jakajan käden joko paremmalla kädellä tai siten, että jakajan pistemäärä menee yli kahdenkymmenenyhden.

*Käyttäjät:* Pelaaja

## Järjestelmän toiminnot
* Pelin aloitus - alkurahamäärä: $1000
* Vinkkitoiminto - jos pelaaja ei pelaa optimaalisesti (ts. perusstrategian mukaisesti), vinkkitoiminto ilmoittaa siitä (valinnainen)
* Pelaajan toiminnot (ks. Pelaajan toiminnot ja pelin kulku)
* Rahamäärän lisäys
* Taustaääni päälle/pois
* Pelin lopetus - sovellusikkunan sulkeminen

## Pelaajan toiminnot ja pelin kulku
### Peliasetelma
Jakajalla on käytössään kahdeksan hyvin sekoitettua korttipakkaa, josta jakaja jakaa pelaajille kortteja. Ennen korttien jakoa suoritetaan panosten asettaminen. Pelaaja voi asettaa panoksen yhdestä viiteen ruutuun. Minimipanos on 50 ja maksimipanos on 500. Kun panos on asetettu, jakaja jakaa kaksi
korttia (yhden kortin kerrallaan) jokaiseen ruutuun, johon panos on asetettu sekä itselleen. Pelaajien kortit jaetaan avoimena (arvopuoli näkyvillä) ja jakajan korteista ensimmäinen suljettuna ja toinen avoimena (arvopuoli piilossa).

### Pelaajan toiminnot
Korttien jaon jälkeen alkaa lisäkorttien jakaminen. Pelaaja voi ottaa yhden tai useamman kortin niin kauan kuin hänen kätensä yhteispistemäärä on 21 tai alle. Pelaaja voi lopettaa korttien ottamisen milloin tahansa, jos korttien yhteispistemäärä ei ylitä 21. Jos otettujen korttien yhteispistemäärä nousee yli 21, pelaaja häviää pelin ja jakaja saa hänen panoksensa. Jos otettujen korttien yhteispistemäärä on 21, niin pelaajalla on nk. ventti, jolloin pelaaja ei voi hävitä muuta kuin jakajan blackjackille. Blackjack on kahden kortin yhdistelmä, jossa toinen korteista on ässä ja toinen kymppi
tai jokin kuvakortti (pl. ässä). Blackjack voittaa kaikki kädet ja tasoittaa jakajan blackjackin kanssa.

#### Pelaajan ratkaisut
Lisäkorttien ottamisen ja ottamisen lopettamisen lisäksi pelaajalla on mahdollisuus seuraaviin ratkaisuihin:
* Tuplaus (double down) - pelaaja tuplaa kätensä panoksen ja ottaa käteensä vain ja ainoastaan yhden kortin, jonka jälkeen pelaajan
vuoro päättyy.
* Splittaus (split) - jos pelaajalla on kaksi saman numeroarvon omaavaa korttia, voi pelaaja jakaa kätensä kahteen käteen, jolloin pelaajan täytyy antaa toinen alkuperäisen kokoinen panos uudelle kädelleen. Molempiin käsiin jaetaan yksi kortti, ja peli jatkuu normaalisti yhtä poikkeusta lukuunottamatta. Jos pelaaja jakaa ässäparin (A-A), molemmille ässille annetaan vain ja ainoastaan yksi kortti, jonka jälkeen käteen ei voi enää vaikuttaa. Jos ässän pariksi tulee kympin arvoinen kortti (eli tulee blackjack), sillä ei ole blackjackin ominaisuuksia vaan sitä pidetään tavallisena kätenä, jonka arvo on 21.
### Jakajan vuoro ja kierroksen päätös
Kun pelaajat ovat lopettaneet korttien ottamisen, on jakajan vuoro nostaa kortteja. Ennen korttien nostamista jakaja kääntää suljetun korttinsa. Jakaja nostaa uuden kortin jos ja vain jos hänen kätensä yhteispistemäärä on 16 tai alle. Jos jakajan käden arvo jää välille 17-20, jakaja voittaa ne kädet, jotka ovat arvoltaan pienempiä kuin jakajan käsi ja häviää niille käsille, joiden arvo on suurempi kuin
hänen kätensä ja tasoittaa samanarvoisten käsien kanssa. Jakajan käden arvon ollessa 21, jakaja voittaa kaikki kädet ja pelaa niiden käsien kanssa tasan, joiden arvo on 21. Jakajan käden mennessä yli, jakaja häviää kaikille käsille, joiden pistemäärä on 21 tai alle (ts. nille käsille, jotka eivät ole menneet yli). Pelaajan voittaessa, saa pelaaja panoksensa kaksinkertaisena takaisin. Pelaajan saadessa
blackjackin, pelaaja saa panoksensa 2,5-kertaisena. Pelaajan hävitessä, jakaja saa pelaajan panoksen.

### Käden arvo

Kortti | Arvo 
-------|----------------------------------------------
2-9    | korttien numero
T-K    | 10
A      | 11, kun käden yhteispistemäärä on alle 21 ja 1, kun käden yhteispistemäärä ylittää 21 A:n arvolla 11

Esimerkiksi: A-9 : 20 (11 + 9), A-9-9 : 19 (1 + 9 + 9)

## Sovelluksen rakenne
Sovellus koostuu GUI:sta (Main-paketissa), kierroslogiikasta (logiikka-paketti), korteista (logiikka.cards-paketti) sekä kierroksen elementeistä eli Käsi-olioista ja Pelaaja-oliosta. Kortteja hallinnoi Jakaja-luokka, jonka staattisilla metodeilla käsketään Jakaja-oliota jakamaan kortteja tai sekoittamaan kortteja.

GUI-sisältää sovelluksen logiikkaa, jotaa yritetään refaktoroida Logiikka-luokkaan seuraavaan releasiin.

Käyttöliittymässä on myös peliäänet sekä taustaäänet, jota hallinnoi Aani-luokka (sounds-paketti). Aani-luokassa on static final-muuttujia, jotka ovat Aani-luokan ilmentymiä. Niillä pystyy hallinnoimaan kaikkia pelin ääniä.

Pelissä on myös strategia-toiminnallisuus, joka kertoo optimaalisen strategian blackjackin pelaamiseen. Sitä hallinnoi strategia-pakkauksessa oleva luokka Strategia. Strategia-luokassa on kolme taulukkoa pehmeille, koville ja parillisille käsille.