@echo off 
cd ../
git pull
echo pulled
git add --all
git commit -a -m "tj"
echo commited
git push origin master
echo pushed
pause