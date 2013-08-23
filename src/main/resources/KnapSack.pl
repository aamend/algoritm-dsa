#!/bin/env/perl

use strict;

my $number     = 5;
my $max_weight = 5;

my @weight = ();
my @profit = ();

# Build up our available items
for ( my $i = 1 ; $i <= $number ; $i++ ) {
	my $w = int( 1 + rand($max_weight) );
	my $p = int( 1 + rand(10) );
	$weight[$i] = $w;
	$profit[$i] = $p;
}

# Create our options matrix
my @options = ();
my @boolean = ();

for ( my $n = 1 ; $n <= $number ; $n++ ) {
	print "\n";
	print "w:$weight[$n], p:$profit[$n]\t";
	for ( my $w = 1 ; $w <= $max_weight ; $w++ ) {

		my $notTakeOption = $options[ $n - 1 ][$w] || 0;
		my $option;

		if ( $weight[$n] <= $w ) {

			# option "take it" can be considered
			# If I take it, I will have a remaining capacity of w - weight[n]
			my $takeOption =
			  $profit[$n] + $options[ $n - 1 ][ $w - $weight[$n] ];
			if ( $takeOption > $notTakeOption ) {

				# take it
				$boolean[$n][$w] = 1;
				$option = $takeOption;
			}
			else {

				# do not take it
				$option = $notTakeOption;
			}
		}
		else {

		# option "take it" is too heavy and cannot be considered in this subsack
			$option = $notTakeOption;
		}

		$options[$n][$w] = $option;
		print "$option\t";
	}
}

print "\n";
print "\n";

for ( my $n = 1 ; $n <= $number ; $n++ ) {
	print "\n";
	print "w:$weight[$n], p:$profit[$n]\t";
	for ( my $w = 1 ; $w <= $max_weight ; $w++ ) {
		my $bool = $boolean[$n][$w] || 0;
		print "$bool\t";
	}
}

print "\n";
print "\n";
print "\n";

my @take = ();
my $w    = $max_weight;
for ( my $n = $number ; $n > 0 ; $n-- ) {
	if ( $boolean[$n][$w] ) {
		$take[$n] = 1;
		$max_weight = $max_weight - $weight[$n];
	}
}

for ( my $n = 1 ; $n <= $number ; $n++ ) {
	print "w:$weight[$n], p:$profit[$n]\t";
	if ( $take[$n] ) {
		print "true\n";
	}
	else {
		print "false\n";
	}
}

