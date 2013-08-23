#!/usr/bin/perl

use strict;

my $min        = 0;
my $max        = 1000;
my $maxBuckets = 15;
my $n          = 100;
my @array;

# -------------------------
# Utils

sub getRandomInt($$) {
	my ( $min, $max ) = @_;
	return int( rand( $max - $min + 1 ) ) + $min;
}

sub arrayToString($) {
	my $string = "";
	my $ref    = shift;
	my @aRef   = @$ref;
	my $count  = 0;
	$string .= "[";
	for my $item (@aRef) {
		if ( $count == 0 ) {
			$string .= "$item";
		}
		else {
			$string .= ",$item";
		}
		$count++;
	}
	$string .= "]";
	return $string;
}

sub hash($$$) {
	my ( $i, $maxNumber, $maxBuckets ) = @_;
	$i-- if ( $i == $maxNumber );
	return int( $i * $maxBuckets / $maxNumber );
}

# -------------------------
# Sorting functions

sub insertionSort($) {

	my $ref        = shift;
	my @array2sort = @$ref;

	# Consider item #0 already sorted
	for ( my $i = 1 ; $i < @array2sort ; $i++ ) {

		# inserting item #i with value $value
		my $value = $array2sort[$i];
		my $pos   = $i - 1;
		while ( $pos >= 0 ) {
			if ( $array2sort[$pos] > $value ) {

				# move larger item to the right
				$array2sort[ $pos + 1 ] = $array2sort[$pos];
			}
			else {

				# left side is sorted
				last;
			}
			$pos--;
		}

		# Move back initial value to the right position
		$array2sort[ $pos + 1 ] = $value;

	}

	return \@array2sort;
}

sub quickSort($$$) {

	my ( $ref, $left, $right ) = @_;
	my @array2sort = @$ref;

	if ( $right - $left > 0 ) {

		my ( $pivot, $newref ) = partition( \@array2sort, $left, $right );
		@array2sort = @$newref;

		$newref     = quickSort( \@array2sort, $left, $pivot - 1 );
		@array2sort = @$newref;

		$newref = quickSort( \@array2sort, $pivot + 1, $right );
		@array2sort = @$newref;
	}

	return \@array2sort;
}

sub partition($$$) {

	my ( $ref, $left, $right ) = @_;
	my @array2sort = @$ref;
	my $length     = @array2sort;

	# Get a random pivot
	my $pivot      = getRandomInt( $left, $right );
	my $pivotValue = $array2sort[$pivot];

	# Swap pivot and rightmost element
	$array2sort[$pivot] = $array2sort[$right];
	$array2sort[$right] = $pivotValue;

	# Create leftmost element as store
	my $store = $left;

	for ( my $i = $left ; $i < $right ; $i++ ) {

		# Sorting element $left
		if ( $array2sort[$i] <= $pivotValue ) {

			# Swap it with store
			my $storeValue = $array2sort[$store];
			$array2sort[$store] = $array2sort[$i];
			$array2sort[$i]     = $storeValue;

			# Increment store
			$store++;
		}
	}

	# Swap pivot with current store
	$array2sort[$right] = $array2sort[$store];
	$array2sort[$store] = $pivotValue;
	return $store, \@array2sort;

}

sub countSort($) {

	my ($ref) = @_;
	my @array2sort = @$ref;

	# Create N buckets
	my @buckets = ();
	for ( my $i = 0 ; $i < @array2sort ; $i++ ) {
		$buckets[ $array2sort[$i] ]++;
	}

	# For each non empty bucket, populate array in sorted orders
	my $pos = 0;
	my $k   = 0;
	for my $buk (@buckets) {
		if ($buk) {
			for ( my $i = 0 ; $i < $buk ; $i++ ) {
				$array2sort[$pos] = $k;
				$pos++;
			}
		}
		$k++;
	}

	return \@array2sort;
}

sub bubbleSort($) {

	my ($ref) = @_;
	my @array2sort = @$ref;

	my $store = @array2sort - 1;
	for ( my $i = @array2sort - 1 ; $i >= 0 ; $i-- ) {
		for ( my $j = 0 ; $j < $i ; $j++ ) {
			if ( $array2sort[$j] > $array2sort[ $j + 1 ] ) {
				my $tmp = $array2sort[$j];
				$array2sort[$j] = $array2sort[ $j + 1 ];
				$array2sort[ $j + 1 ] = $tmp;
			}
		}
	}

	return \@array2sort;

}

sub mergeSort($) {

	my ($ref)      = @_;
	my @array2sort = @$ref;
	my @left       = ();
	my @right      = ();

	# 1 or 0 element remain, array sorted
	if ( @array2sort <= 1 ) {
		return \@array2sort;
	}

	my $k   = 0;	
	my $mid = int( @array2sort / 2 );
	for ( my $i = 0 ; $i < $mid ; $i++ ) {
		$left[$k] = $array2sort[$i];
		$k++;
	}

	$k = 0;
	for ( my $i = $mid ; $i < @array2sort ; $i++ ) {
		$right[$k] = $array2sort[$i];
		$k++;
	}

	$ref  = mergeSort( \@left );
	@left = @$ref;

	$ref   = mergeSort( \@right );
	@right = @$ref;

	$ref = merge( \@left, \@right );
	my @merge = @$ref;

	return \@merge;

}

sub merge($$) {

	my ( $leftRef, $rightRef ) = @_;
	my @left       = @$leftRef;
	my @right      = @$rightRef;
	my @merge      = ();
	my $leftCount  = 0;
	my $rightCount = 0;
	my $i          = 0;

	while ( $leftCount < @left || $rightCount < @right ) {

		# At least 1 item still to add

		if ( $leftCount == @left ) {

			# Data on right only
			$merge[$i] = $right[$rightCount];
			$rightCount++;
		}
		elsif ( $rightCount == @right ) {

			# Data on left only
			$merge[$i] = $left[$leftCount];
			$leftCount++;
		}
		else {

			# Data on both side
			if ( $right[$rightCount] <= $left[$leftCount] ) {
				$merge[$i] = $right[$rightCount];
				$rightCount++;
			}
			else {
				$merge[$i] = $left[$leftCount];
				$leftCount++;
			}
		}

		$i++;

	}

	return \@merge;

}

sub bucketSort($$$) {

	my ( $ref, $maxValue, $maxBuckets ) = @_;
	my @array2sort = @$ref;
	my @buckets    = ();

	# Create my k buckets
	for ( my $i = 0 ; $i < @array2sort ; $i++ ) {

		my $hash    = hash( $array2sort[$i], $maxValue, $maxBuckets );
		my @kBucket = ();
		my $kRef    = $buckets[$hash];
		if ($kRef) {
			@kBucket = @$kRef;
		}

		push( @kBucket, $array2sort[$i] );
		$buckets[$hash] = \@kBucket;

	}

	# Sort each bucket with insertionSort
	for ( my $k = 0 ; $k < @buckets ; $k++ ) {
		my $ref = $buckets[$k];
		if ($ref) {
			my @buckArray = @$ref;
			$ref = insertionSort( \@buckArray );
			$buckets[$k] = $ref;
		}
	}

	# Populate array in order
	my $idx = 0;
	for ( my $k = 0 ; $k < @buckets ; $k++ ) {
		my $ref = $buckets[$k];
		next if ( !$ref );
		my @sortedBuckArray = @$ref;
		for my $value (@sortedBuckArray) {
			$array2sort[$idx] = $value;
			$idx++;
		}
	}

	return \@array2sort;

}

# -------------------------
# MAIN

# Populate our array

@array = ();
for ( my $i = 0 ; $i < $n ; $i++ ) {
	push( @array, getRandomInt( $min, $max ) );
}

print "This is my unsorted array \n" . arrayToString( \@array ) . "\n\n";
print "This is my sorted array insertionSort \n"
  . arrayToString( insertionSort( \@array ) ) . "\n";
print "This is my sorted array bubbleSort\n"
  . arrayToString( bubbleSort( \@array ) ) . "\n";
print "This is my sorted array mergeSort\n"
  . arrayToString( mergeSort( \@array ) ) . "\n";
print "This is my sorted array bucketSort\n"
  . arrayToString( bucketSort( \@array, $max, $maxBuckets ) ) . "\n";
print "This is my sorted array countSort\n"
  . arrayToString( countSort( \@array ) ) . "\n";
print "This is my sorted array quickSort\n"
  . arrayToString( quickSort( \@array, 0, $n - 1 ) ) . "\n";
