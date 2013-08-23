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


i=0
while [[ $i -lt $MAX ]] ; do
        COUNT[$i]=0
        let i=i+1
done

for ITEM in ${ARRAY[*]} ; do
        COUNT[$ITEM]=`expr ${COUNT[$ITEM]} + 1`
done

idx=0
for IDX in ${!COUNT[*]} ; do
        if [[ ${COUNT[$IDX]} -ne 0 ]] ; then
                i=0
                while [[ $i < ${COUNT[$IDX]} ]] ; do
                        SORTED[$idx]=$IDX
                        let i=i+1
                        let idx=idx+1
                done
        fi
done


# Print array
echo "******************************"
echo "THIS IS MY SORTED ARRAY"
echo "******************************"
for IDX in ${!SORTED[*]} ; do
        echo -e "$IDX\t${SORTED[$IDX]}"
done
