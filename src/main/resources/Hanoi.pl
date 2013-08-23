#!/usr/bin/env perl

use strict;

my $number       = 15;
my $maxsize      = 50;
my @unsortedpile = ();
my @priestheap   = ();
my @sortedpile   = ();

sub generateDisk() {

	for ( my $i = 0 ; $i <= $number ; $i++ ) {
		push( @unsortedpile, int( 1 + rand( $maxsize - 1 ) ) );
	}

	print "This is my unsorted pile\n";
	print "@unsortedpile\n";
}

sub addToHeap($) {
	my ($value) = shift;
	push( @priestheap, $value );
	my $idx = @priestheap - 1;
	while ( $idx >= 0 ) {
		my $parentIdx = int( ( $idx - 1 ) / 2 );

		# Max heap
		if ( $priestheap[$parentIdx] < $priestheap[$idx] ) {

			# need to swap them
			my $tmp = $priestheap[$parentIdx];
			$priestheap[$parentIdx] = $priestheap[$idx];
			$priestheap[$idx]       = $tmp;

			# go to next parent
			$idx = $parentIdx;
		}
		else {
			last;
		}
	}

}

sub maxAndHeapify() {

	my $max = shift(@priestheap);

	for ( my $idx = 0 ; $idx < @priestheap ; $idx++ ) {

		my $left  = 2 * $idx + 1;
		my $right = 2 * $idx + 2;
		my $largest;

		if ( $left >= 0 && $priestheap[$left] > $priestheap[$idx] ) {
			$largest = $left;
		}
		else {
			$largest = $idx;
		}

		if ( $right >= 0 && $priestheap[$right] > $priestheap[$largest] ) {
			$largest = $right;
		}

		# If one child is greater, promot the greatest one
		if ( $largest != $idx ) {
			my $tmp = $priestheap[$largest];
			$priestheap[$largest] = $priestheap[$idx];
			$priestheap[$idx]     = $tmp;
		}
	}

	return $max;

}

generateDisk();

while (@unsortedpile) {
	addToHeap( pop(@unsortedpile) );
}

print "This is my heap\n";
print "@priestheap\n";

while (@priestheap) {
	push( @sortedpile, maxAndHeapify() );
}

print "This is my sorted pile\n";
print "@sortedpile\n";
