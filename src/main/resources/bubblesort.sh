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

# Print array
VALUES=`shuf -i ${MIN}-${MAX} -n $SIZE`
echo "******************************"
echo "THIS IS MY UNSORTED ARRAY"
echo "******************************"
i=0
for VALUE in $VALUES ; do
        ARRAY[$i]=$VALUE
        echo -e "$i\t${ARRAY[$i]}"
        let i=i+1
done

i=${#ARRAY[*]}
let i=i-1

while [[ $i -ge 0 ]] ; do
        j=0
        while [[ $j -lt $i ]] ; do
                k=`expr $j + 1`
                if [[ ${ARRAY[$j]} -gt ${ARRAY[$k]} ]] ; then
                        # Swap j+1 and j
                        TMP=${ARRAY[$k]}
                        ARRAY[$k]=${ARRAY[$j]}
                        ARRAY[$j]=$TMP
                fi
                let j=j+1
        done
        let i=i-1
done


echo "******************************"
echo "THIS IS MY SORTED ARRAY"
echo "******************************"
for IDX in ${!ARRAY[*]} ; do
        echo -e "$IDX\t${ARRAY[$IDX]}"
done

