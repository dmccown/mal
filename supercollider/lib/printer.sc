Printer {
	*prStr {
		|sexp, printReadably = false|
		^switch (sexp.class,
			MALTrue, { "true" },
			MALFalse, { "false" },
			MALNil, { "nil" },
			MALInt, { sexp.value.asString },
			MALSymbol, { sexp.value.asString },
			MALString, { if (printReadably) {
				sexp.value.asCompileString.replace("\n", "\\n")
			} { sexp.value }
			},
			MALKeyword, { ":" ++ sexp.value },
			MALList, { Printer.prList(sexp.value, printReadably, "(", ")") },
			MALVector, { Printer.prList(sexp.value, printReadably, "[", "]") },
			MALMap, { Printer.prList(sexp.value.asPairs, printReadably, "{", "}") },
			MALAtom, { "(atom %)".format(Printer.prStr(sexp.value, printReadably)) },
			Function, { "#<fn>" },
			Func, { "#<func>" },
			{ "unknown type".error.throw })
	}

	*prList {
		|sexps, printReadably, starter, ender|
		var representations = sexps.collect {
			|sexp| Printer.prStr(sexp, printReadably)
		};
		^starter ++ representations.join(" ") ++ ender
	}
}
