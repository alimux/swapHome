#!/bin/bash
echo copie librairies vers swapHome
cd /home/etudiants/99005132/JEE/fakeDB/
echo compilation
ant dist
cp /home/etudiants/99005132/JEE/fakeDB/dist/users.jar /home/etudiants/99005132/JEE/swapHome/lib/
echo terminé !
