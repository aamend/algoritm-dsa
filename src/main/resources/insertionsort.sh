#!/bin/bash

usage(){
        echo "Usage:"
        echo "$0 -a <minimum> -z <maximum value> -s <number of items>"
        exit 1
}

while getopts "a:z:s:" option
do
        case "$option" in
                a)MIN="$OPTARG";;
                z)MAX="$OPTARG";;
                s)SIZE="$OPTARG";;
                \?)usage;;
        esac
done

if [[ ! -n $MIN || ! -n $MAX || ! -n $SIZE ]] ; then
        usage
fi

VALUES=`shuf -i $MIN-$MAX -n $SIZE`
i=0
echo "**************************"
echo "THIS IS MY UNSORTED ARRAY"
echo "**************************"
for VALUE in $VALUES ; do
        UNSORTED[$i]=$VALUE
        echo -e "$i\t$VALUE"
        i=`expr $i + 1`
done

i=1
while [[ $i -lt $SIZE ]] ; do

        CURRENT=${UNSORTED[$i]}
        j=`expr $i - 1`
        while [[ $j -ge 0 ]] ; do
                if [[ ${UNSORTED[$j]} -gt $CURRENT ]] ; then
                        k=`expr $j + 1`
                        UNSORTED[$k]=${UNSORTED[$j]}
                else
                        break
                fi
                j=`expr $j - 1`
        done
        i=`expr $i + 1`
        j=`expr $j + 1`
        UNSORTED[$j]=$CURRENT
done


i=0
echo "**************************"
echo "THIS IS MY SORTED ARRAY"
echo "**************************"
for VALUE in ${UNSORTED[*]} ; do
        echo -e "$i\t$VALUE"
        i=`expr $i + 1`
done
